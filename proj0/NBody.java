public class NBody{
    public static void main(String[] args){
        //lack error checking
        /** for animation */
        StdDraw.enableDoubleBuffering();

    // collect all inputs
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        In in = new In(filename);
        Planet[] planets = NBody.readPlanets(filename);
        double radius = NBody.readRadius(filename);

    /** draw the universe in discrete interval */
        for (int i = 0; i*dt <= T; i++){
            //calculate force for each planets
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];

            for (int j = 0; j < planets.length; j++){
                xForces[j] = planets[j].calcNetForceExertedByX(planets);
                yForces[j] = planets[j].calcNetForceExertedByY(planets);
            }
            //calculate movement for each planets and update
            for (int j = 0; j < planets.length; j++){
                planets[j].update(dt, xForces[j], yForces[j]);
            }
            //draw
            /** Sets up the universe so it goes from 
		  * -100, -100 up to 100, 100 */
            StdDraw.setScale(-radius, radius);
            String imageToDraw = "images/starfield.jpg";
            StdDraw.picture(0, 0, imageToDraw);
            for (Planet p: planets){
                p.draw(p);
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
}
        
  
   
}

    public static double readRadius(String file_path){		
        In in = new In(file_path);
        int num = in.readInt();
        double radius = in.readDouble();
       return radius;
    }
    public static Planet[] readPlanets(String file_path){		
        In in = new In(file_path);
        int num = in.readInt();
        double radius = in.readDouble();
        Planet[] planets = new Planet[num];
        for (int i =0; i < num; i++){
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            Planet planet = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
            planets[i] = planet;
        }
       return planets;
    }
}