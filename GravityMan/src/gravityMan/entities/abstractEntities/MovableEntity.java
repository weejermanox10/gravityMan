package gravityMan.entities.abstractEntities;

public interface MovableEntity extends Entity {
	double getVelX();
	double getVelY();
	double getVelMag();
	double getVelAngleRad();
	
	void setVelX(double val);
	void setVelY(double val);
	void setVelAngleRad(double theta);
	void scaleVel(double factor);
	//TODO update these interfaces
}
