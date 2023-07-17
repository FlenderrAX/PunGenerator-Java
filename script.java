import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Random;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

public class script {
    public static void main(String[] args){
        Random rm = new Random();
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrez le mot que vous voulez : ");
        String searched_name = sc.nextLine().toUpperCase();
        List<String> found_words = new ArrayList<>();
        try {
            File filename = new File("ods9.txt");
            Scanner readf = new Scanner(filename);
            while (readf.hasNextLine())
            {
                String data = readf.nextLine();
                if (data.contains(searched_name)){
                    found_words.add(data);
                }
            }
        } catch (FileNotFoundException e ){
            System.out.println("An error has occured !");
            e.printStackTrace();
        }
        if (found_words.isEmpty())
        {
            System.out.println("Aucun jeu de mot n'a été trouvé avec le mot proposé !");
        }
        else {
            int rd_nb = rm.nextInt(found_words.size());
            String final_word = found_words.get(rd_nb);
            System.out.println(final_word);
            String ask_copy = "Voulez vous copiez votre jeu de mot ? {O:N}: ";
            System.out.println(ask_copy);
            String copy_answer = sc.nextLine().toUpperCase();
            String s = final_word.toUpperCase() + ":" + final_word.toLowerCase();
            if (Objects.equals(copy_answer, "O")){
                StringSelection stringSelection = new StringSelection(s);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);
                System.out.println("Le mot a été copié avec succès !");
                System.exit(0);
            } else if (Objects.equals(copy_answer, "N")){
                System.exit(0);
            } while (!Objects.equals(copy_answer, "O") || !Objects.equals(copy_answer, "N"))
            {
                ask_copy = "Veuillez entrer une valeur correcte {O:N} : ";
                System.out.println(ask_copy);
                copy_answer = sc.nextLine().toUpperCase();
                if (Objects.equals(copy_answer, "O")){
                    StringSelection stringSelection = new StringSelection(s);
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clipboard.setContents(stringSelection, null);
                    System.out.println("Le mot a été copié avec succès !");
                    System.exit(0);
                } else if (Objects.equals(copy_answer, "N")){
                    System.exit(0);
                }
            }
        }
    }
}
