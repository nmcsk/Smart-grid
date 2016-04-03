/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btp_2016;

import static java.time.Clock.system;
import java.util.Arrays;
import java.util.Stack;

/**
 *
 * @author sachin
 */
public class Calculate_Paths {
   
    static int[][] priority_matrix;//rows contains edges, columns contains nodes
    
    public static Node_Edge Priority_Computation(Node_Edge Data)
    {
        int[][] graph;//adjacency matrix
        boolean[] visited;
        int DISCOM=0,i,j,n=Data.No.size(),m=Data.Ed.size();
        for(i=0;i<n;i++)
        {
            if(Data.No.get(i).Static_Priority==10)//static priority 10 -> DISCOM
                DISCOM++;
        }
        graph = new int[n][n];
	visited = new boolean[n];
        priority_matrix=new int[m][n];
        
        for (i=0;i<n;i++) 
        {
		for(j=0;j<n; j++) 
                {
			graph[i][j]=0;
		}
                for(j=0;j<m;j++)
                {
                    priority_matrix[j][i]=0;
                }
                visited[i]=false;
	}
        //Initialization of adjacency matrix
        
        
        
        for(i=0;i<m;i++)
        {
            graph[Data.Ed.get(i).a.ID][Data.Ed.get(i).b.ID]=1;
            graph[Data.Ed.get(i).b.ID][Data.Ed.get(i).a.ID]=1;//for unidirected graph need to change this
        }
        
        
        //adjacency matrix initialized
        Stack<Integer> stack = new Stack<Integer>();
        for(i=0;i<DISCOM;i++)
        {
            for(j=DISCOM;j<n;j++)
            {
                while(!stack.empty())
                    stack.pop();
                findAllPath(stack,graph,i,j,visited,Data,i,j);
            }
        }
       
        //make static priority matrix
        int[] Static_Priorities;
        Static_Priorities=new int[n];
        for(i=0;i<n;i++)
            Static_Priorities[i]=Data.No.get(i).Static_Priority;
          
        //update priorities of edges
        int x;
        for(i=0;i<m;i++)
        {
            x=0;
            for(j=0;j<n;j++)
            {
                x=x+(priority_matrix[i][j]*Static_Priorities[j]);
            }
            Data.Ed.get(i).Priority=x;
        }

        //update priorites of nodes
        for(i=0;i<n;i++)
        {
            x=0;
            for(j=0;j<n;j++)
            {
                if(graph[i][j]==1)
                {
                    x+=Data.Ed.get(find_edge_id(Data,i,j)).Priority;
                }
            }
            Data.No.get(i).Dynamic_Priority=x;
        }

        return Data;
    }
    
    public static int[] getAdjacentNodes(int[][] graph,int s)
    {
		
	int[] row = graph[s];
	int[] adjNodes = null ;
        int nodeCount = 0;

	for (int i = 0; i < row.length; i++)
        {
		if(row[i]==1)
			nodeCount++;
	}
		
	adjNodes = new int[nodeCount];
	nodeCount = 0;

	for (int i = 0; i < row.length; i++) 
        {
		if(row[i]==1){
			adjNodes[nodeCount] = i;
			nodeCount++;
		}
	}	
	return adjNodes;
    }
    
    public static int find_edge_id(Node_Edge Data,int s,int d)
    {
        int id;
        for(int i=0;i<Data.Ed.size();i++)
        {
            if((Data.Ed.get(i).a.ID==s)&&(Data.Ed.get(i).b.ID==d))
                return Data.Ed.get(i).ID;
            if((Data.Ed.get(i).a.ID==d)&&(Data.Ed.get(i).b.ID==s))
                return Data.Ed.get(i).ID;   
        }
        return 0;
    }
    
    public static void findAllPath(Stack<Integer> stack,int[][] graph,int s,int d,boolean[] visited,Node_Edge Data,int source,int destination)
    {		
        
        stack.add(s);
        if(s==d)
        {
            int i;
            Object[] temp = stack.toArray();

            for(i=1;i<temp.length;i++)
            { 
                int x=find_edge_id(Data,(int)temp[i],(int)temp[i-1]);//edge id
                priority_matrix[x][source]+=1;
                priority_matrix[x][destination]+=1;
            }
	}
		
	if(visited[s] != true)
            visited[s] = true;
		
	int[] adjNodes = getAdjacentNodes(graph,s);
		

	if(adjNodes.length>0)
        {	
		for (int i = 0; i < adjNodes.length; i++) 
                {
                    if(visited[adjNodes[i]]!=true)
                    {
			findAllPath(stack,graph, adjNodes[i], d,visited,Data,source,destination);
                    }
		}
	}
		
	visited[s] = false;
	stack.remove(stack.size()-1);
    }
}
