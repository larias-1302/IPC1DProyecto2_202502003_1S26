package sistema;

import modelos.Usuario;

public class Leaderboard {

    public static void ordenar(Usuario[] usuarios) {

        for (int i = 0; i < usuarios.length - 1; i++) {
            for (int j = 0; j < usuarios.length - i - 1; j++) {

                if (usuarios[j].xp < usuarios[j + 1].xp) {

                    Usuario temp = usuarios[j];
                    usuarios[j] = usuarios[j + 1];
                    usuarios[j + 1] = temp;
                }
            }
        }
    }
}