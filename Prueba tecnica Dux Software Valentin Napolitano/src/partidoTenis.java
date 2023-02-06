//Valentin Napolitano

import java.util.Scanner;
import java.util.Random;

public class partidoTenis {
    static String player1, player2, tournament;
    static int prob1,  p1Score, p2Score, sets;
    static Random rand = new Random();

    static int[] probArray1;



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Solicitar datos del partido
        System.out.print("Nombre del jugador 1: ");
        player1 = sc.nextLine();
        System.out.print("Nombre del jugador 2: ");
        player2 = sc.nextLine();
        System.out.print("Nombre del torneo: ");
        tournament = sc.nextLine();
        System.out.print("Cantidad al mejor de (3 o 5) sets: ");
        sets = sc.nextInt();
        System.out.print("Probabilidad de " + player1 + " ganar (1-100%): ");
        prob1 = sc.nextInt();

        // Iniciar la simulación del partido
        play(prob1, sets);

        // Preguntar si se desea jugar la revancha
        System.out.print("¿Jugar revancha? (s/n): ");
        String rematch = sc.next();
        if (rematch.equalsIgnoreCase("s")) {
            p1Score = 0;
            p2Score = 0;
            // Volver a jugar la simulación del partido
            play(prob1, sets);

        }
    }



    //Se elige el ganador de cada punto segun la probabilidad asignada
    private static int probGame(int prob) {

        probArray1 = new int[100];
        for (int f = 0; f < prob; f++) {
            probArray1[f] = 1;
        }

        for (int n = prob; n < prob; n++) {
            probArray1[n] = 2;
        }

        int choose = probArray1[rand.nextInt(100)];
        return choose;

    }

    //Metodo donde se simula el partido
    private static void play(int prob, int sets) {

        p1Score = 0;
        p2Score = 0;


        int winner = 0;

        int point1 = 0, point2 = 0;

        int p1Games = 0, p2Games = 0, saque = 0;

        int setR = 0;

        if (sets == 3){
            setR = 2;
        }else if (sets == 5){
            setR = 3;
        }

        int[] score1 = new int[sets];
        int[] score2 = new int[sets];

        int count = 0;



        while (p1Score < setR && p2Score < setR) {


            p1Games = 0;
            p2Games = 0;


            while (winCondition(p1Games, p2Games)) {

                winner = probGame(prob);



                if (point1 == 0 && point2 == 0){

                    saque++;

                    if (saque % 2 == 0) {
                        System.out.println("\nSaque de " + player2 + "\n");
                    } else {
                        System.out.println("\nSaque de " + player1 + "\n");
                    }
                }


                if (winner == 1) {


                    if (point1 == 40 && point2 == 40){
                        point1 = 50;
                        System.out.println(player1 + " gana el punto \n");
                        System.out.println(player1 + " AV " + "\n" + player2 + " = " + point2);
                        System.out.println(" ");

                    }else if (point1 == 40 && point2 == 50){
                        point1 = 40;
                        point2 = 40;
                        System.out.println(player1 + " gana el punto \n");
                        System.out.println(player1 + " = " + point1 + "\n" + player2 + " = " + point2);
                        System.out.println(" ");
                    }


                    else if (point1 == 40 || point1 == 50) {
                        p1Games++;
                        System.out.println(player1 + " gana el punto \n");
                        System.out.println(player1 + " gana el game");
                        System.out.println("Resultado parcial del game: ");
                        System.out.println(p1Games + " | " + p2Games);
                        point1 = 0;
                        point2 = 0;
                        System.out.println(" ");


                    }else {
                        if (point1 == 30){
                            point1 = point1 + 10;
                        }else {
                            point1 = point1 + 15;
                        }

                        System.out.println(player1 + " gana el punto \n");
                        System.out.println(player1 + " = " + point1 + "\n" + player2 + " = " + point2);
                        System.out.println(" ");


                    } if (point2 == 40 && point1 == 50){
                        point1 = 40;
                        point2 = 40;
                        System.out.println(player2 + " gana el punto \n");
                        System.out.println(player1 + " = " + point1 + "\n" + player2 + " = " + point2);}
                    System.out.println(" ");

                } else {

                    if (point2 == 40 && point1 == 40){
                        point2 = 50;
                        System.out.println(player2 + " gana el punto \n");
                        System.out.println(player1 + " = " + point1 + "\n" + player2 + " AV");
                        System.out.println(" ");
                    }

                    if (point2 == 40 || point2 == 50) {
                        p2Games++;
                        System.out.println(player2 + " gana el punto \n");
                        System.out.println(player2 + " gana el game");
                        System.out.println("Resultado parcial del game: ");
                        System.out.println(p1Games + " | " + p2Games);
                        point1 = 0;
                        point2 = 0;
                        System.out.println(" ");

                    }else {
                        if (point2 == 30){
                            point2 = point2 + 10;
                        }else {
                            point2 = point2 + 15;
                        }

                        System.out.println(player2 + " gana el punto \n");
                        System.out.println(player1 + " = " + point1 + "\n" + player2 + " = " + point2);
                        System.out.println(" ");


                    }


                }

            }

            if (p1Games == 7 || (p1Games == 6 && p2Games < 5)){
                p1Score++;
            }

            if (p2Games == 7 || (p2Games == 6 && p1Games < 5)){
                p2Score++;
            }

            System.out.println("Resultado parcial del partido: ");
            System.out.println(player1 + " " + p1Score + " | " + player2 + " " + p2Score);
            System.out.println(" ");

            score1[count] = p1Games;
            score2[count] = p2Games;
            count++;



        }
        // Mostrar resultado final del partido
        System.out.println("Resultado final: " + p1Score + "-" + p2Score);
        if (p1Score > p2Score) {
            System.out.println(player1 + " gana el partido");
        } else {
            System.out.println(player2 + " gana el partido");
        }

        System.out.print("Torneo: " + tournament);
        System.out.println(" ");
        System.out.print(player1 + " | ");
        for (int i = 0; i < count; i++ ) {
            System.out.print(score1[i] + " | ");
        }

        System.out.println(" ");

        System.out.print(player2 + " | ");
        for (int i = 0; i < count; i++ ) {
            System.out.print(score2[i] + " | ");
        }
        System.out.println(" ");

    }

    //Se especifica que si ambos jugadores tienen 5 games, gana el que consiga 7

    public static boolean winCondition(int p1Games, int p2Games){

        boolean dif = true;
        if (p1Games >= 5 && p2Games >= 5){
            dif = !(p1Games==7 || p2Games==7);
        }else{
            dif = p1Games<6 && p2Games<6;
        }
        return dif;

    }

}


