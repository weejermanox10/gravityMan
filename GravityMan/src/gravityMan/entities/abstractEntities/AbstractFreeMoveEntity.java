package gravityMan.entities.abstractEntities;

import gravityMan.util.Vector2d;

public abstract class AbstractFreeMoveEntity extends AbstractMovableEntity {
	protected double mass;
	protected Vector2d force;
	protected Vector2d momentum;

	protected double torque;
	protected double inertia;
	protected double angMomentum;

	public AbstractFreeMoveEntity(double x, double y, double mass,
			double inertia) {
		super(x, y);

		this.mass = mass;
		this.inertia = inertia;
		angMomentum = 0;
		torque = 0;
		force = new Vector2d(0, 0);
		momentum = new Vector2d(0, 0);
	}

	public void clearForce() {
		force = force.scale(0);
	}

	public void applyForce(Vector2d force) {
		this.force = this.force.add(force);
	}

	public void applyForce(Vector2d force, Vector2d displacement) {
		this.force = this.force.add(force);
		torque += force.cross(displacement);
	}

	public double getMass() {
		return mass;
	}

	@Override
	public void update(int delta) {
		// TODO: change to improved Euler or RK4?
		// linear
		momentum = momentum.add(force.scale(delta));
		force.zero();
		vel = momentum.scale(1 / mass);
		// angular
		angMomentum += torque * delta;
		torque = 0;
		angVel = angMomentum / inertia;

		super.update(delta);
	}

}
