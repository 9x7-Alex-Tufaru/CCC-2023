import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Service {

    public Service() {
    }


    public String lastStanding(int nrFighters, String tournament) {
        String finalString = "";
        while (tournament.length() > 1) {
            finalString = "";
            for (int i = 0; i < tournament.length() - 1; i += 2) {
                Fighter f1 = new Fighter(String.valueOf(tournament.charAt(i)));
                Fighter f2 = new Fighter(String.valueOf(tournament.charAt(i + 1)));

                finalString += f1.match(f2).getStyle();
            }
            tournament = finalString;
        }
        return finalString;
    }

    public String generateBracket(int nrFighters, int nrRock, int nrPaper, int nrScissors) {
        String finalString = "";
        //first RRRP
        int numberRounds = (int) (Math.log(nrFighters) / Math.log(2));
        int index = numberRounds;
        while (index > 1) {
            if (nrRock >= Math.pow(2, index) - 1 && nrPaper >= 2) {
                String str = "R";
                finalString += str.repeat((int) (Math.pow(2, index) - 1));
                finalString += "P";
                nrRock -= (int) (Math.pow(2, index) - 1);
                nrPaper -= 1;
            }
            index--;
        }
        index = numberRounds;
        while (nrRock >= 1 && nrScissors >= 2) {
            finalString += "RS";
            nrRock -= 1;
            nrScissors -= 1;
        }
        while (nrPaper >= 0) {
            finalString += "P";
            nrPaper -= 1;
        }
        while (nrScissors > 0) {
            finalString += "S";
            nrScissors -= 1;
        }
        return finalString;
    }


    public String newGenBracket1(int nrFighters, int nrRock, int nrPaper, int nrScissors) {
        String finalString = "";
        int nr = (int) (Math.log(nrFighters) / Math.log(2)) - 1;
        while (nr > 0 && nrRock > 0) {
            if (nrRock >= Math.pow(2, nr) - 1 && nrPaper >= 1) {
                finalString += "P";
                String str = "R";
                finalString += str.repeat((int) (Math.pow(2, nr) - 1));
                nrRock -= (int) (Math.pow(2, nr) - 1);
                nrPaper -= 1;
            } else {
                if (nrPaper >= (int) Math.pow(2, nr) - nrRock && (int) Math.pow(2, nr) - nrRock > 0) {
                    String str = "R";
                    finalString += str.repeat((int) nrRock);
                    str = "P";
                    int pp = (int) Math.pow(2, nr) - nrRock;
                    finalString += str.repeat((int) pp);
                    nrPaper -= pp;
                    nrRock = 0;
                }


            }
            nr--;
        }

        while (nrRock > 0) {
            nrRock--;
            finalString += "R";
        }

        while (nrPaper > 0) {
            nrPaper--;
            finalString += "P";
        }

        while (nrScissors > 0) {
            nrScissors--;
            finalString += "S";
        }

        return finalString;
    }


    public String newGenBracket2(int nrRock, int nrPaper, int nrScissors, int nrL, int nrY) {
        String finalString = "";
        while (nrScissors > 0) {
            nrScissors--;
            finalString += "S";
        }

        while (nrL > 0) {
            nrL--;
            finalString += "L";
        }
        while (nrPaper > 0) {
            nrPaper--;
            finalString += "P";
        }

        while (nrY > 0) {
            nrY--;
            finalString += "Y";
        }
        while (nrRock > 0) {
            nrRock--;
            finalString += "R";
        }


        return finalString;
    }

    public void simulateTournament() {

        try {
            File input = new File("CCC31MARCH/src/input.txt");
            File output = new File("CCC31MARCH/src/output.txt");
            Scanner scanner = new Scanner(input);
            FileWriter out = new FileWriter(output);

            String firstLine = scanner.nextLine();
            List<String> prim = Arrays.stream(firstLine.split(" ", 0)).toList();
            int N = Integer.valueOf(prim.get(0).trim());
            int M = Integer.valueOf(prim.get(1).trim());

            while (N > 0) {

                List<String> tournamentList = Arrays.stream(scanner.nextLine().split(" ")).toList();
                int nrRock = Integer.valueOf(tournamentList.get(0).substring(0, tournamentList.get(0).length() - 1));
                int nrPaper = Integer.valueOf(tournamentList.get(1).substring(0, tournamentList.get(1).length() - 1));
                int nrScissors = Integer.valueOf(tournamentList.get(2).substring(0, tournamentList.get(2).length() - 1));
                int nrLiz = Integer.valueOf(tournamentList.get(3).substring(0, tournamentList.get(3).length() - 1));
                int nrY = Integer.valueOf(tournamentList.get(4).substring(0, tournamentList.get(4).length() - 1));
                String tournament = this.newGenBracket2(nrRock, nrPaper, nrScissors, nrLiz, nrY);
                out.write(lastStanding(M, tournament));
                out.write(":->");
                out.write(tournament);
                out.write("\n");

                N--;
            }
            scanner.close();
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
