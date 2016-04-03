/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btp_2016;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swingViewer.Viewer;
import org.graphstream.ui.swingViewer.ViewerListener;
import org.graphstream.ui.swingViewer.ViewerPipe;

/**
 *
 * @author nmcsk
 */
public class Graph_Data implements ViewerListener{
    
    static boolean loop = true;
    boolean isDeleted =false;
    String deletedNodeId;

    
    public void Data() throws IOException{
        
    
        ArrayList<Institution> Nodes = new ArrayList<>();        
        ArrayList<Edges> Edg = new ArrayList<>();
        int i,j,flag,del=0,priority;
        String mostimportant="";
        
        Nodes = Static_Values.getNodes();        
        Edg = Static_Values.getEdges(Nodes);//Assign initial data about all the edges and nodes
       
        
        Node_Edge Data = new Node_Edge(Nodes,Edg);
        Node_Edge Result= Calculate_Paths.Priority_Computation(Data);
        Nodes=Result.No;
        Edg=Result.Ed;
        Make_Graph graph = new Make_Graph();
        SingleGraph Graph = graph.Graphing(Nodes, Edg);
        
        Viewer viewer = Graph.display();
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.HIDE_ONLY);
        ViewerPipe fromViewer = viewer.newViewerPipe();
        fromViewer.addViewerListener(this);
        fromViewer.addSink(Graph);
       
       priority=0;
        for(i=0;i<Nodes.size();i++)
        {
            if((Nodes.get(i).Dynamic_Priority>priority)&&(!Nodes.get(i).Name.contains("DISCOM")))
            {
                mostimportant=Nodes.get(i).Name;
                priority=Nodes.get(i).Dynamic_Priority;
            }
        }
        
       Node mostImp = Graph.getNode(mostimportant);
       boolean fl=true;
       
       for(long stop=System.nanoTime()+TimeUnit.SECONDS.toNanos(3);stop>System.nanoTime();)      
       {
            if(fl)
            {
                mostImp.addAttribute("ui.class", "hide");
                try
                {
                    Thread.sleep(500);
                    fl=false;
                }
                catch(Exception e)
                {
                
                }
            }
            else
            {
                 mostImp.addAttribute("ui.class", "show");
                 try
                 {
                    Thread.sleep(500);
                    fl=true;
                 }
                catch(Exception e)
                {

                }
            }
   
        }
        fl=true;
        mostImp.addAttribute("ui.class", "hide");
        
        
        while(loop)
        {
            
            if(isDeleted)
            {
                isDeleted=false;
                //BufferedReader br = new BufferedReader(new FileReader("template/temp.txt"));
                String line=deletedNodeId;
               // line=br.readLine();
                flag=0;//for nodes
              
                Node deletednode=Graph.getNode(deletedNodeId);
                deletednode.addAttribute("ui.class", "important");

                for(Edge ed:Graph.getEachEdge())
                {
                    if(ed.getNode0().equals(deletednode)||ed.getNode1().equals(deletednode))
                    {
                       ed.addAttribute("ui.class", "delete");
                       try{
                            Thread.sleep(1000);
                       }
                       catch(Exception chutpaat)
                       {
                           System.out.println(chutpaat);
                       }
                       Graph.removeEdge(ed); 
                    }
                }
               
                Graph.removeNode(deletednode);
                
                for(i=0;i<Nodes.size();)
                {
                    if(flag==1)
                    {
                        Nodes.get(i).ID=Nodes.get(i).ID-1;
                        i++;
                    }
                    else
                    {
                        if(Nodes.get(i).Name.equals(line.toString()))
                        {
                            flag=1;
                            Nodes.remove(Nodes.get(i));
                            del=i;
                        }
                        else
                            i++;
                    }
                }

                for(j=0;j<Edg.size();)
                {
                    if(Edg.get(j).a.Name.equals(line.toString()) || Edg.get(j).b.Name.equals(line.toString()))
                    {
                        Edg.remove(j);;
                    }
                    else
                    {
                        j++;
                    }
                }

                for(j=0;j<Edg.size();j++)
                {
                    Edg.get(j).ID=j;
                }           

                if(Nodes.size()==2)
                {
                    loop=false;
                    break;
                }

                Data = new Node_Edge(Nodes,Edg);

                Result= Calculate_Paths.Priority_Computation(Data);
                Nodes=Result.No;
                Edg=Result.Ed;
                Iterator<Institution> Node_Iterator = Nodes.iterator();
                Node_Iterator = Nodes.iterator();
                while (Node_Iterator.hasNext()) 
                {
                    Institution temp=Node_Iterator.next();    
                    Node x=Graph.getNode(temp.Name);
                    String s;
                    s=Integer.toString(temp.Dynamic_Priority);
                    x.setAttribute("data", s);
                }
                for (Node node : Graph)
                {
                    String s1=node.getId();
                    String s=s1+", "+node.getAttribute("data");
                    node.addAttribute("ui.label", s);
                }
                priority=0;
                for(i=0;i<Nodes.size();i++)
                {
                    if((Nodes.get(i).Dynamic_Priority>priority)&&(!Nodes.get(i).Name.contains("DISCOM")))
                    {
                        mostimportant=Nodes.get(i).Name;
                        priority=Nodes.get(i).Dynamic_Priority;
                    }
                        
                }
                mostImp = Graph.getNode(mostimportant);
                for(long stop=System.nanoTime()+TimeUnit.SECONDS.toNanos(3);stop>System.nanoTime();)      
                {
                    System.out.println("ho");
                     if(fl)
                     {
                         mostImp.addAttribute("ui.class", "hide");
                         try
                         {
                             Thread.sleep(500);
                             fl=false;
                         }
                         catch(Exception e)
                         {

                         }
                     }
                     else
                     {
                          mostImp.addAttribute("ui.class", "show");
                          try
                          {
                             Thread.sleep(500);
                             fl=true;
                          }
                         catch(Exception e)
                         {

                         }
                     }

                 }
                 fl=true;
                 mostImp.addAttribute("ui.class", "hide");
            }
            
            fromViewer.pump();
        }    
        
        return;            
    }
     
    public void buttonReleased(String id) 
     {
         if(!id.contains("DISCOM"))
         {
             deletedNodeId=id;
            System.out.println("Button released on node "+id);
            try
            {
                PrintWriter writer = new PrintWriter("template/temp.txt", "UTF-8");
                writer.println(id);
                writer.close();
                isDeleted=true;

            } 
            catch (FileNotFoundException ex)
            {
                Logger.getLogger(Make_Graph.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (UnsupportedEncodingException ex) 
            {
                Logger.getLogger(Make_Graph.class.getName()).log(Level.SEVERE, null, ex);
            }
  
         }
         else
         {
             System.out.println("DISCOM cannot be deleted");
         }
         return;
    }
    
     public void buttonPushed(String id){
                System.out.println("Button pushed on node "+id);
     } 
     public void viewClosed(String id) {
       loop = false;
    }
}
