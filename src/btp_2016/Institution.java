/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btp_2016;

import java.util.jar.Attributes.Name;

/**
 *
 * @author sachin
 */
public class Institution {
   
    String Name;
    int ID;
    int Static_Priority;  
    int Dynamic_Priority;
    int Power;
   
    public Institution(String n, int id, int sp) {
        this.Name=n;
        this.ID=id;
        this.Static_Priority=sp;
        this.Dynamic_Priority=sp;
        this.Power=0;
    }
}
