package com.example.laboratorio4_escena3d;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
import android.view.MotionEvent;
import android.widget.Toast;

public class Renderiza extends GLSurfaceView implements Renderer{
	
	Context contexto;

	private Plano plano;
	private ArbolPiramide arbolP;
	private ArbolCircular arbolC;
	private Sol sol;
	private Montaña montana;
	private Casa casa;
	
	private Camino camino;
	private Piramide piramide;
	private int alto, ancho;
	
	private Boton boton;
	
	
	private int rotacionY;
	private int direccionZ;
	private int speed;
	
	GL10 glg;
	public Renderiza(Context context) {
		super(context);
		this.contexto = context;
		this.setRenderer(this);
		this.requestFocus();
		this.setFocusableInTouchMode(true);
		this.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
	}
	
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig arg1) {
		speed = 1;
		casa = new Casa();
		sol = new Sol();
		plano = new Plano();
		boton = new Boton();
		montana = new Montaña();
		camino = new Camino();
		arbolP = new ArbolPiramide();
		arbolC = new ArbolCircular();
		/* Habilita el modo de sombreado plano */
		gl.glShadeModel(GL10.GL_FLAT);
		/* Color de fondo */
		gl.glClearColor(153/255f, 211/255f, 234/255f, 1);
		/* Especifica el valor de limpiado */
		gl.glClearDepthf( 1.0f );
		/* Habilita el ocultamiento de superficies */
		gl.glEnable(GL10.GL_DEPTH_TEST);
	}
	@Override
	public void onDrawFrame(GL10 gl) {
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		float aspectoRatio = (float)ancho/(float)alto;
		
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
//		GLU.gluOrtho2D(gl, -2, 12, -2, 22);
		
		GLU.gluPerspective(gl, 60f, aspectoRatio, 1.0f, 50.0f );
		GLU.gluLookAt(gl, 0, 0, 5, 0, 0, -20, 0, 1, 0);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		
				
		gl.glLoadIdentity();
		gl.glRotatef(rotacionY*speed, 0, 1, 0);
		gl.glTranslatef(0, 0, 1*direccionZ);
//		
		
		gl.glTranslatef (0f, -5f, -9.0f );
//		gl.glRotatef(10,1,0,0);
		
		gl.glPushMatrix();
		gl.glTranslatef (0f, 0f, 25.0f );
		gl.glScalef(50, 1, 200);
		gl.glColor4f(200/255f, 168/255f, 81/255f, 1);
		plano.dibuja(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glTranslatef(0, 0.1f, 0);
		gl.glTranslatef (0f, 0f, 25.0f );
		camino.dibuja(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glTranslatef(10, 3, 0);
		gl.glScalef(3, 3, 3);
		casa.dibuja(gl);
		gl.glPopMatrix();
		
		dibujarMontanas(gl);
		dibujarArboles(gl);
		
		gl.glPushMatrix();
		gl.glLoadIdentity();
		gl.glRotatef(rotacionY*speed, 0, 1, 0);

		gl.glTranslatef (0f, -5f, -9.0f );
		gl.glTranslatef(0, 25, -30);
		gl.glScalef(0.5f, 0.5f, 0.5f);
		sol.dibuja(gl);
		gl.glPopMatrix();
		
		
		dibujarInterface(gl);
	}
	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		ancho = width;
		alto = height;
		gl.glViewport(0, 0, width, height);
		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent e){
			float x = e.getX();
			float y = e.getY();
			float posx, posy;
			if (e.getAction() == MotionEvent.ACTION_DOWN) {
				
				posx = (x * 14/ (float) ancho)-2;
				posy = (24 - y * 24 / (float) alto)-2;
	//			Toast.makeText(contexto, "POSICION"+"X:"+posx+" Y:"+posy, Toast.LENGTH_SHORT).show();
				if (puntoEstaDentroDelRectangulo(posx, posy, 4, 0, 2, 2)){
					direccionZ++;
				}
				if (puntoEstaDentroDelRectangulo(posx, posy, 4, -2, 2, 2)){					
					direccionZ--;
				}
				if (puntoEstaDentroDelRectangulo(posx, posy, 2, -1, 2, 2)){
					rotacionY-=30;
				}
				if (puntoEstaDentroDelRectangulo(posx, posy, 6, -1, 2, 2)){
//					boolean sx;
//					if(cx<5){
//						cx++;
//					}else if(cz<5){
//						cz++;
//					}else if(cx>1 & sx){
//						cx--;
//					}else if(cz>1 & sz){
//						cz--;
//					}	
					rotacionY+=30;
				}
				if (puntoEstaDentroDelRectangulo(posx, posy, -2, 18, 2, 2)){
									
				}
			}
			if(e.getAction() == MotionEvent.ACTION_UP){
				speed = 1;
			}
		return true;
	}
	private boolean puntoEstaDentroDelRectangulo(float posx, float posy, 
			int x, int y, int ancho, int alto){
		
		return (x < posx && posx < x + ancho && 
				y < posy && posy < y + alto);
	}
	private void dibujarInterface(GL10 gl){

		gl.glPushMatrix();
		gl.glDisable(GL10.GL_DEPTH_TEST);

		gl.glLoadIdentity();
		gl.glTranslatef(0f, -8.5f, -15);
		gl.glRotatef(90, 1, 0, 0);
		gl.glRotatef(180, 0, 0, 1);
		gl.glScalef(1f, 0.8f, 1);
		gl.glColor4f(1, 0, 0, 1);
		boton.dibuja(gl);
		
		gl.glLoadIdentity();
		gl.glTranslatef(0f, -10.5f, -15);
		gl.glRotatef(90, 1, 0, 0);
		gl.glScalef(1f, 0.8f, 1);
		gl.glColor4f(1, 0, 1, 1);
		boton.dibuja(gl);

		gl.glLoadIdentity();
		gl.glTranslatef(2f, -9.5f, -15);
		gl.glRotatef(90, 1, 0, 0);
		gl.glRotatef(-90, 0, 0, 1);
		gl.glScalef(1f, 0.8f, 1);
		gl.glColor4f(1, 0, 1, 1);
		boton.dibuja(gl);
		
		gl.glLoadIdentity();
		gl.glTranslatef(-2f, -9.5f, -15);
		gl.glRotatef(90, 1, 0, 0);
		gl.glRotatef(90, 0, 0, 1);
		gl.glScalef(1f, 0.8f, 1);
		gl.glColor4f(0, 0, 1, 1);
		boton.dibuja(gl);
		
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glPopMatrix();
	}
	public void dibujarArboles(GL10 gl){
		for(int i=-80; i<80; i+=15){
			gl.glPushMatrix();
			gl.glTranslatef(10, 1, i);
			gl.glScalef(2, 2, 2);
			arbolC.dibuja(gl);
			gl.glPopMatrix();
		}
		for(int i=-80; i<80; i+=15){
			gl.glPushMatrix();
			gl.glTranslatef(-10, 1, i);
			gl.glScalef(2, 2, 2);
			arbolC.dibuja(gl);
			gl.glPopMatrix();
		}
	}
	public void dibujarMontanas(GL10 gl){
		for(int i=-80; i<80; i+=15){
			gl.glPushMatrix();
			gl.glTranslatef(20, 8, i);
			gl.glScalef(2f, 10.5f, 7);
			montana.dibuja(gl);
			gl.glPopMatrix();
		}
		for(int i=-80; i<80; i+=15){
			gl.glPushMatrix();
			gl.glTranslatef(-20, 8, i);
			gl.glScalef(2f, 10.5f, 7);
			montana.dibuja(gl);
			gl.glPopMatrix();
		}
	}
}
