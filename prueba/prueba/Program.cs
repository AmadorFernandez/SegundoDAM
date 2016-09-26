using System;

namespace prueba
{
	class MainClass
	{
		public static void Main (string[] args)
		{
			Tablero juego = new Tablero (24,80);

			while (true) {

				Console.SetCursorPosition(0,0);
				for (int i = 0; i < juego.getmTablero().GetLength(0); i++) {

					for (int j = 0; j < juego.getmTablero().GetLength(1); j++) {


						Console.Write(juego.getmTablero()[i,j]);

					}


				}

				juego.avanzarGeneracion ();






			}





		}
	}
}
