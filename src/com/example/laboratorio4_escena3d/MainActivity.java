package com.example.laboratorio4_escena3d;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {
	private Button boton1;
	private Button boton2;
	private Button boton3;
	private Button boton4;
	Renderiza superficie;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* Ventana sin título */
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		/* Se establece las banderas de la ventana de esta Actividad */
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		/* Orientación de la pantalla vertical (PORTRAIT) u horizontal(LANDSCAPE) */
//		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		RelativeLayout pantalla = (RelativeLayout) getLayoutInflater().inflate(
				R.layout.activity_main, null);
		superficie = new Renderiza(this);
		pantalla.addView(superficie);		/*
		 * Activity <- GLSurfaceView : Coloca la Vista de la Superficie del
		 * OpenGL como un Contexto de ésta Actividad.
		 */
		setContentView(pantalla);
		// 5setContentView(R.layout.activity_main);
	}
}
