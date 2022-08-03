
public class Planet{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV,
            double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    };
    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    };
    public double calcDistance(Planet p){
        double x_dist = p.xxPos - this.xxPos;
        double y_dist = p.yyPos - this.yyPos;
        double dist = Math.sqrt(x_dist * x_dist + y_dist * y_dist);
        return dist;
    }
    public double calcForceExertedBy(Planet p){
        double G = 6.67E-11;
        double dist = this.calcDistance(p);
        double force = G * this.mass * p.mass/(dist * dist);
        return force;
    }
    public double calcForceExertedByX(Planet p){
        double force = this.calcForceExertedBy(p);
        double dx = p.xxPos - this.xxPos;
        double dist = this.calcDistance(p);
        double force_x = force * dx/dist;
        return force_x;
    }
    public double calcForceExertedByY(Planet p){
        double force = this.calcForceExertedBy(p);
        double dy = p.yyPos - this.yyPos;
        double dist = this.calcDistance(p);
        double force_y = force * dy/dist;
        return force_y;
    }
    public double calcNetForceExertedByX(Planet[] allPlanets){
        double net_force = 0;
        for (Planet p: allPlanets){
            if (!this.equals(p))
                net_force += this.calcForceExertedByX(p);
        }
        return net_force;
    }
    public double calcNetForceExertedByY(Planet[] allPlanets){
            
        double net_force = 0;
        for (Planet p: allPlanets){
            if (!this.equals(p))
                net_force += this.calcForceExertedByY(p);
        }
        return net_force;
    }
    public void update(double dt, double fX, double fY){
        double a_x = fX/this.mass;
        double a_y = fY/this.mass;
        double p_x_dist = a_x * dt;
        double p_y_dist = a_y * dt;
        this.xxVel = this.xxVel + a_x * dt;
        this.yyVel = this.yyVel + a_y * dt;
        this.xxPos = this.xxPos + xxVel * dt;
        this.yyPos = this.yyPos + yyVel * dt;
    }

    public void draw(){
        String img_path = "images/" + this.imgFileName;
        StdDraw.picture(this.xxPos, this.yyPos, img_path);
    }
}

