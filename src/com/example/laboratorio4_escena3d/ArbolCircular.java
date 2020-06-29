package com.example.laboratorio4_escena3d;

import javax.microedition.khronos.opengles.GL10;

public class ArbolCircular {

	Circulo circulo;
	Cubo cubo;
	public ArbolCircular(){
		circulo = new Circulo(3,15, true);
		cubo = new Cubo();
	}
	public void dibuja(GL10 gl){
		gl.glTranslatef(0,2,0);
		gl.glPushMatrix();
		gl.glTranslatef(0, -1.3f, 0);
		gl.glScalef(0.3f, 2f, 0.3f);
		gl.glColor4f(100/255f, 60/255f, 40/255f, 1);
		cubo.dibuja(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
		gl.glColor4f(34/255f, 177/255f, 76/255f, 1);
		gl.glTranslatef(0, 3, 0);
		gl.glScalef(0.8f, 0.8f, 0.8f);
		circulo.dibuja(gl);
		gl.glRotatef(90, 0, 1, 0);
		circulo.dibuja(gl);
		gl.glRotatef(70, 0, 1, 0);
		circulo.dibuja(gl);
		gl.glRotatef(-70-70, 0, 1, 0);
		circulo.dibuja(gl);
		gl.glPopMatrix();
	}
}
