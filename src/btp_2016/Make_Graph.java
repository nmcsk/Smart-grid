/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btp_2016;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.graphstream.ui.swingViewer.Viewer;
import org.graphstream.ui.swingViewer.ViewerListener;
import org.graphstream.ui.swingViewer.ViewerPipe;

/**
 *
 * @author
 */
public class Make_Graph {
    
    public SingleGraph Graphing(ArrayList<Institution> Nodes, ArrayList<Edges> Edg)
    {
        String styleSheet="graph {fill-color: #7FFFD4;} node {text-alignment: under;text-size: 15px;} node#simulate {text-mode: normal;size: 100px;} node.important{size:100px;} edge.delete{fill-color : red;} node.show{size:100px;}node.hide{size:65px;} ";
        Iterator<Institution> Node_Iterator = Nodes.iterator();
        while (Node_Iterator.hasNext()) {
            Institution temp=Node_Iterator.next();    
            styleSheet=styleSheet+"node#"+temp.Name+ " {fill-mode: image-scaled;fill-image: url('images/"+temp.Name+".png');size: 65px;}";
	}
        
        SingleGraph graph = new SingleGraph("POWER GRID");
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        Node_Iterator = Nodes.iterator();
        while (Node_Iterator.hasNext()) {
            Institution temp=Node_Iterator.next();    
            Node x=graph.addNode(temp.Name);
            String s;
            s=Integer.toString(temp.Dynamic_Priority);
            x.addAttribute("data", s);
            
	}
        
        int i=0;
        Iterator<Edges> Edge_Iterator = Edg.iterator();
        while (Edge_Iterator.hasNext()) {
            Edges temp=Edge_Iterator.next();
            graph.addEdge(Integer.toString(i++),temp.a.Name, temp.b.Name);
	}       
        for (Node node : graph) {
            String s1=node.getId();
            String s=s1+", "+node.getAttribute("data");
            node.addAttribute("ui.label", s);
            
        }
        
        
        graph.addAttribute("ui.stylesheet", styleSheet);
        
        

        return graph;
    }
    
    
    
    
     
   
}

