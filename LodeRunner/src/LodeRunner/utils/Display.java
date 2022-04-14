package LodeRunner.utils;

public class Display {

    public static String[] loadingPage = new String[]{
            "Lode Runner",
            "",
            "by best software inc",
            "",
            "",
            "Select your Game Mode:",
            "1 - Local Game",
            "2 - Online Game",
            "3 - Scores",
            "",
            "",
            "copyright ©2022 B GOUN",
            "copyright ©2022 S LERX",
            "",
            "licenced by",
            "NINTUFR OF FRANCE INC"
    };

    public static String[] namePage = new String[]{
            "Controls : ",
            "",
            "Z : up    A : break left bloc       ",
            "S : down  E : break right bloc      ",
            "Q : left  W : break bloc bottom left",
            "D : right C : break bloc bottom right",
            "",
            "",
            "Choose your nickname :",
    };

    public static String[] multiPage = new String[]{
            "Mode Multijoueur",
            "",
            "1 - Héberger la partie",
            "2 - Se connecter à une partie",
            "",
    };

    public static String[] hostPage = new String[]{
            "Mode Multijoueur",
            "",
            "Vous hebergez la partie.",
            "En attente de connexion",
            "",
    };

    public static String[] ipPage = new String[]{
            "Mode Multijoueur",
            "",
            "Entrez l'IP de la partie",
            "",
    };

    public static String[] waitPage = new String[]{
            "Mode Multijoueur",
            "",
            "En attente de l'autre joueur ...",
            "",
    };

    public static String[] modePage = new String[]{
            "Mode Multijoueur",
            "",
            "1 - Coopération",
            "2 - VS",
    };


    public static void title() {
        System.out.println("\n\n" +
                """
                        ██╗      ██████╗ ██████╗ ███████╗    ██████╗ ██╗   ██╗███╗   ██╗███╗   ██╗███████╗██████╗
                        ██║     ██╔═══██╗██╔══██╗██╔════╝    ██╔══██╗██║   ██║████╗  ██║████╗  ██║██╔════╝██╔══██╗
                        ██║     ██║   ██║██║  ██║█████╗      ██████╔╝██║   ██║██╔██╗ ██║██╔██╗ ██║█████╗  ██████╔╝
                        ██║     ██║   ██║██║  ██║██╔══╝      ██╔══██╗██║   ██║██║╚██╗██║██║╚██╗██║██╔══╝  ██╔══██╗
                        ███████╗╚██████╔╝██████╔╝███████╗    ██║  ██║╚██████╔╝██║ ╚████║██║ ╚████║███████╗██║  ██║
                        ╚══════╝ ╚═════╝ ╚═════╝ ╚══════╝    ╚═╝  ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═══╝╚══════╝╚═╝  ╚═╝
                        """);
    }

}
