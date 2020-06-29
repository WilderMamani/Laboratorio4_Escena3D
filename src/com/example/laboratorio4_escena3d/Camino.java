package com.example.laboratorio4_escena3d;

import javax.microedition.khronos.opengles.GL10;

public class Camino {

	Plano plano;
	
	public Camino (){
		plano = new Plano();
	}
	public void dibuja(GL10 gl){
		gl.glPushMatrix();
		gl.glScalef(3, 1, 200);
		gl.glColor4f(60/255f, 60/255f, 60/255f, 1);
		plano.dibuja(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glColor4f(1, 1, 0, 1);
		gl.glTranslatef(0,0.1f, 0);
		gl.glTranslatef(0.2f, 0, 0);
		gl.glScalef(0.1f, 1, 200);
			plano.dibuja(gl);
		gl.glTranslatef(-4, 0, 0);
			plano.dibuja(gl);
		gl.glPopMatrix();
	}
}
