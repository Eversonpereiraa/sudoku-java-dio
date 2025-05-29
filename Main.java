
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Sudoku sudoku = new Sudoku();
        String saveFile = "savegame.txt";

        while (true) {
            sudoku.printBoard();
            if (sudoku.isComplete()) {
                System.out.println("Parabéns! Você completou o Sudoku corretamente!");
                break;
            }

            System.out.println("Opções:");
            System.out.println("1 - Fazer jogada (linha coluna número)");
            System.out.println("2 - Desfazer última jogada");
            System.out.println("3 - Pedir dica");
            System.out.println("4 - Salvar jogo");
            System.out.println("5 - Carregar jogo");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            if (option == 0) {
                System.out.println("Jogo encerrado. Obrigado por jogar!");
                break;
            }

            switch (option) {
                case 1:
                    System.out.println("Digite linha (1-9), coluna (1-9) e número (1-9) separados por espaço:");
                    String[] parts = scanner.nextLine().split(" ");
                    if (parts.length != 3) {
                        System.out.println("Entrada inválida.");
                        break;
                    }
                    try {
                        int row = Integer.parseInt(parts[0]) - 1;
                        int col = Integer.parseInt(parts[1]) - 1;
                        int num = Integer.parseInt(parts[2]);
                        if (!sudoku.isValidMove(row, col, num)) {
                            System.out.println("Jogada inválida.");
                        } else {
                            sudoku.setNumber(row, col, num);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida.");
                    }
                    break;
                case 2:
                    sudoku.undo();
                    break;
                case 3:
                    sudoku.giveHint();
                    System.out.println("Pressione Enter para continuar...");
                    scanner.nextLine();
                    break;
                case 4:
                    sudoku.saveGame(saveFile);
                    System.out.println("Pressione Enter para continuar...");
                    scanner.nextLine();
                    break;
                case 5:
                    sudoku.loadGame(saveFile);
                    System.out.println("Pressione Enter para continuar...");
                    scanner.nextLine();
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }

        scanner.close();
    }
}
