import java.io.*;
import java.util.NoSuchElementException;

public class Project1 {
    public static void main(String[] args) throws IOException {

        //INIT
        BufferedReader br = new BufferedReader(
                new FileReader(args[0]));
        BufferedWriter bw = new BufferedWriter(
                new FileWriter(args[1]));
        StringBuilder sb = new StringBuilder();
        Factory factory = new FactoryImpl();

        // INPUT
        String line;
        while ((line = br.readLine()) != null) {
            String[] tokens = line.split("\s");

            if (!(tokens.length > 0)) //empty line
                continue;

            /*
                AF: addFirst        - "AF <product_id> <product_value>"
                AL: addLast         - "AL <product_id> <product_value>"
                RF: removeFirst     - "RF"
                RL: removeLast      - "RL"
                F:  find            - "F <product_id>"
                U:  update          - "U <product_id> <updated_value>"
                G:  get             - "G <index>"
                P:  print           - "P"
            */

            switch (tokens[0]) {
                case "AF":
                    factory.addFirst(
                            new Product(
                                    Integer.parseInt(tokens[1]),
                                    Integer.parseInt(tokens[2])
                            )
                    );

                    break;
                case "AL":
                    factory.addLast(
                            new Product(
                                    Integer.parseInt(tokens[1]),
                                    Integer.parseInt(tokens[2])
                            )
                    );

                    break;
                case "RF":
                    try {
                        Product p = factory.removeFirst();
                        sb.append(p.toString()).append(System.lineSeparator());

                    } catch (NoSuchElementException e) {
                        sb.append("Factory is empty").append(System.lineSeparator());
                    }

                    break;
                case "RL":
                    try {
                        Product p = factory.removeLast();
                        sb.append(p.toString());
                        sb.append(System.lineSeparator());

                    } catch (NoSuchElementException e) {
                        sb.append("Factory is empty.").append(System.lineSeparator());
                    }

                    break;
                case "F":
                    try {
                        Product p = factory.find(
                                Integer.parseInt(tokens[1]));
                        sb.append(p.toString()).append(System.lineSeparator());

                    } catch (NoSuchElementException e) {
                        sb.append("Product not found.").append(System.lineSeparator());
                    }

                    break;
                case "U":
                    try {
                        Product p = factory.update(
                                Integer.parseInt(tokens[1]),
                                Integer.parseInt(tokens[2])
                        );
                        sb.append(p.toString()).append(System.lineSeparator());

                    } catch (NoSuchElementException e) {
                        sb.append("Product not found.").append(System.lineSeparator());
                    }

                    break;
                case "G":
                    try {
                        Product p = factory.get(
                                Integer.parseInt(tokens[1])
                        );
                        sb.append(p.toString()).append(System.lineSeparator());

                    } catch (IndexOutOfBoundsException e) {
                        sb.append("Index out of bounds.").append(System.lineSeparator());
                    }
                    break;
                case "P":
                    sb.append(factory).append(System.lineSeparator());
                    break;
            }
        }
        br.close();

        //OUTPUT
        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }
}
