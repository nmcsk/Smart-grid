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
import java.io.FileOutputStream;
import java.util.ArrayList;


/**
 *
 * @author sachin
 */
public class Static_Values  {
    //Institutions  = new SKT_SubHeader_s;
    public static ArrayList<Institution> getNodes() throws FileNotFoundException, IOException
    {
            BufferedReader br = new BufferedReader(new FileReader("template/Nodes.txt"));
            String line;
            ArrayList<Institution> Nodes = new ArrayList<Institution>();
            line = br.readLine();
            line=br.readLine();
            while(line!=null)
            {
                String[] nodes = line.split(" ");//delimiting using space
                Institution temp = new Institution(nodes[0],Integer.parseInt(nodes[1]),Integer.parseInt(nodes[2]));
                Nodes.add(temp);
                line=br.readLine();
            }
            return(Nodes);
    }
    
    public static ArrayList<Edges> getEdges(ArrayList<Institution> Nodes) throws FileNotFoundException, IOException
    {
            BufferedReader br = new BufferedReader(new FileReader("template/Edges.txt"));
            String line;
            ArrayList<Edges> Edges = new ArrayList<Edges>();
            line = br.readLine();
            line=br.readLine();
            int i=0;
            while(line!=null)
            {
                String[] edges = line.split(" ");//delimiting using space
                Edges temp = new Edges(Nodes.get(Integer.parseInt(edges[0])), Nodes.get(Integer.parseInt(edges[1])),i++);
                Edges.add(temp);
                line=br.readLine();
            }
            return(Edges);
    }
            
};