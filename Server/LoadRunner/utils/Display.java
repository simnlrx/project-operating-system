package LoadRunner.utils;

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
            "",
            "",
            "copyright ©2022 B GOUN",
            "copyright ©2022 S LERX",
            "",
            "licenced by",
            "NINTUFR OF FRANCE INC"
    };

    public static String[] levelPage = new String[]{
           "Choose your level: ",
           "",
           "1 - Easy",
           "",
           "2 - Medium",
           "",
           "3 - Hard"
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
           "Choose name of player 1 :",
    };


    public static void title() {
        System.out.println("                                                                                                                                                      \n" +
                " ____                _____          _____        _____              _____    ____   ____  _____   ______  _____   ______        ______        _____   \n" +
                "|    |          ____|\\    \\     ___|\\    \\   ___|\\    \\         ___|\\    \\  |    | |    ||\\    \\ |\\     \\|\\    \\ |\\     \\   ___|\\     \\   ___|\\    \\  \n" +
                "|    |         /     /\\    \\   /    /\\    \\ |    |\\    \\       |    |\\    \\ |    | |    | \\\\    \\| \\     \\\\\\    \\| \\     \\ |     \\     \\ |    |\\    \\ \n" +
                "|    |        /     /  \\    \\ |    |  |    ||    | |    |      |    | |    ||    | |    |  \\|    \\  \\     |\\|    \\  \\     ||     ,_____/||    | |    |\n" +
                "|    |  ____ |     |    |    ||    |__|    ||    | |    |      |    |/____/ |    | |    |   |     \\  |    | |     \\  |    ||     \\--'\\_|/|    |/____/ \n" +
                "|    | |    ||     |    |    ||    .--.    ||    | |    |      |    |\\    \\ |    | |    |   |      \\ |    | |      \\ |    ||     /___/|  |    |\\    \\ \n" +
                "|    | |    ||\\     \\  /    /||    |  |    ||    | |    |      |    | |    ||    | |    |   |    |\\ \\|    | |    |\\ \\|    ||     \\____|\\ |    | |    |\n" +
                "|____|/____/|| \\_____\\/____/ ||____|  |____||____|/____/|      |____| |____||\\___\\_|____|   |____||\\_____/| |____||\\_____/||____ '     /||____| |____|\n" +
                "|    |     || \\ |    ||    | /|    |  |    ||    /    | |      |    | |    || |    |    |   |    |/ \\|   || |    |/ \\|   |||    /_____/ ||    | |    |\n" +
                "|____|_____|/  \\|____||____|/ |____|  |____||____|____|/       |____| |____| \\|____|____|   |____|   |___|/ |____|   |___|/|____|     | /|____| |____|\n" +
                "  \\(    )/        \\(    )/      \\(      )/    \\(    )/           \\(     )/      \\(   )/       \\(       )/     \\(       )/    \\( |_____|/   \\(     )/  \n" +
                "   '    '          '    '        '      '      '    '             '     '        '   '         '       '       '       '      '    )/       '     '   \n" +
                "                                                                                                                                   '                  ");
    }

}