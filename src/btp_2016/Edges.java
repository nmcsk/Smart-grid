/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btp_2016;

/**
 *
 * @author sachin
 */
public class Edges {
    
    Institution a;
    Institution b;
    int ID;
    int Priority;
    int Power;
    
    public Edges(Institution x, Institution y, int id) {
        this.a=x;
        this.b=y;
        this.ID=id;
        this.Priority=0;
        this.Power=0;
    }
}
