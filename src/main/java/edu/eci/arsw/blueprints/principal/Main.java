/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.principal;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import edu.eci.arsw.blueprints.*;
import edu.eci.arsw.blueprints.filters.BlueprintFilter;
import edu.eci.arsw.blueprints.filters.BlueprintFilterRedundance;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;

/**
 *
 * @author 2103021
 */
public class Main {
    
    public static void main(String[] args) throws BlueprintNotFoundException, BlueprintPersistenceException{
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bps = ac.getBean(BlueprintsServices.class);
        
        Point[] p = new Point[]{new Point(13,1), new Point(20,5)};
        Blueprint bp = new Blueprint("Juan", "plan1", p);
        Point[] p1 = new Point[]{new Point(3,10), new Point(2,6)};
        Blueprint bp1 = new Blueprint("Camilo", "plan2", p1);
        Point[] p2 = new Point[]{new Point(31,10), new Point(24,54)};
        Blueprint bp2 = new Blueprint("Andrew", "plan3", p2);
        bps.addNewBlueprint(bp);
        bps.addNewBlueprint(bp1);
        bps.addNewBlueprint(bp2);
        
        System.out.println("PRUEBA PROGRAMA");
        
        System.out.println(bps.getBlueprintsByAuthor("Juan"));
        System.out.println(bps.getBlueprint("Juan", "plan1"));
        
        System.out.println("PRUEBA FILTROS");
        
        Point[] p3 = new Point[]{new Point(13,1), new Point(13,1), new Point(13, 1)};
        Blueprint bp3 = new Blueprint("YO", "name",p3);
        BlueprintFilter bpf = new BlueprintFilterRedundance();
        Blueprint bpres = bpf.filter(bp3);
        System.out.println(bpres.getPoints());
    }
}
