package gravityMan.entities.connectors;

import gravityMan.entities.abstractEntities.AbstractMovableEntity;
import gravityMan.util.Vector2d;
import static org.lwjgl.opengl.GL11.*;

public class SpringConnector extends AbstractConnector {
	public double springConstant, equilLen;
	public double minDist = 1;

	public SpringConnector(AbstractMovableEntity anchorA,
			AbstractMovableEntity anchorB, double springConstant,
			double equilLen) {
		super(anchorA, anchorB);
		this.springConstant = springConstant;
		this.equilLen = equilLen;
	}
	public SpringConnector(AbstractMovableEntity anchorA, Vector2d dispA,
			AbstractMovableEntity anchorB, Vector2d dispB, double springConstant,
			double equilLen) {
		super(anchorA, dispA, anchorB, dispB);
		this.springConstant = springConstant;
		this.equilLen = equilLen;
	}

	@Override
	public void apply() {
		updateA();
		updateB();
		
		Vector2d dist = locA.sub(locB);
		Vector2d x;
		if (dist.getMag() > minDist) {
			x = dist.scale(equilLen / dist.getMag());
		} else {
			x = dist.scale(equilLen / minDist);
		}
		Vector2d force = dist.sub(x).scale(springConstant);

		a.applyForce(force.scale(-1), locA.sub(a.getLocation()));
		b.applyForce(force, locB.sub(b.getLocation()));
	}

	public void draw() {
		glBegin(GL_LINES);
		glColor3d(0, 1, 1);
		glVertex2d(locA.getX(), locA.getY());
		glVertex2d(locB.getX(), locB.getY());
		glEnd();
	}
}
