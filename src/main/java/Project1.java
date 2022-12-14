import java.io.*;
import java.util.NoSuchElementException;

public class Project1 {
    public static void main(String[] args) {


        //INIT
        BufferedWriter bw = null;
        BufferedReader br = null;
        try {
            br = new BufferedReader(
                    new FileReader(args[0]));
            bw = new BufferedWriter(
                    new FileWriter(args[1]));

        } catch (FileNotFoundException e) {
            System.err.println("Exception caught: Input file not found.");
            System.exit(1);

        } catch (IOException e) {
            System.err.println("Exception caught: Output file could not be opened.");
            System.exit(1);

        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Exception caught: Input and output file paths must be provided.");
            System.exit(1);

        }

        StringBuilder sb = new StringBuilder();
        Factory factory = new FactoryImpl();
        String factoryIsEmpty = "Factory is empty.";
        String productNotFound = "Product not found.";
        String indexOutOfBounds = "Index out of bounds.";
        String line;

        Long tic = System.currentTimeMillis();
        // INPUT
        try {
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("\s");

                if (!(tokens.length > 0)) //empty line
                    continue;

            /*
                AF: addFirst            - "AF <product_id> <product_value>"
                AL: addLast             - "AL <product_id> <product_value>"
                RF: removeFirst         - "RF"
                RL: removeLast          - "RL"
                F:  find                - "F <product_id>"
                U:  update              - "U <product_id> <updated_value>"
                G:  get                 - "G <index>"
                P:  print               - "P"
                RI: removeIndex         - "RI <index>"
                RP: removeProduct       - "RP <product_value>"
                FD: filterDuplicates    - "FD"
                R:  reverse             - "R"
                A:  add                 - "A <index> <product_id> <product_value>"
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
                            sb.append(factoryIsEmpty).append(System.lineSeparator());
                        }

                        break;
                    case "RL":
                        try {
                            Product p = factory.removeLast();
                            sb.append(p.toString()).append(System.lineSeparator());

                        } catch (NoSuchElementException e) {
                            sb.append(factoryIsEmpty).append(System.lineSeparator());
                        }

                        break;
                    case "F":
                        try {
                            Product p = factory.find(
                                    Integer.parseInt(tokens[1]));
                            sb.append(p.toString()).append(System.lineSeparator());

                        } catch (NoSuchElementException e) {
                            sb.append(productNotFound).append(System.lineSeparator());
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
                            sb.append(productNotFound).append(System.lineSeparator());
                        }

                        break;
                    case "G":
                        try {
                            Product p = factory.get(
                                    Integer.parseInt(tokens[1])
                            );
                            sb.append(p.toString()).append(System.lineSeparator());

                        } catch (IndexOutOfBoundsException e) {
                            sb.append(indexOutOfBounds).append(System.lineSeparator());
                        }

                        break;
                    case "P":
                        sb.append(factory).append(System.lineSeparator());

                        break;
                    case "RI":
                        try {
                            Product p = factory.removeIndex(
                                    Integer.parseInt(tokens[1])
                            );
                            sb.append(p.toString()).append(System.lineSeparator());

                        } catch (IndexOutOfBoundsException e) {
                            sb.append(indexOutOfBounds).append(System.lineSeparator());
                        }

                        break;
                    case "RP":
                        try {
                            Product p = factory.removeProduct(
                                    Integer.parseInt(tokens[1])
                            );
                            sb.append(p.toString()).append(System.lineSeparator());

                        } catch (NoSuchElementException e) {
                            sb.append(productNotFound).append(System.lineSeparator());
                        }

                        break;
                    case "FD":
                        sb.append(factory.filterDuplicates()).append(System.lineSeparator());

                        break;
                    case "R":
                        factory.reverse();
                        sb.append(factory).append(System.lineSeparator());

                        break;
                    case "A":
                        try {
                            factory.add(
                                    Integer.parseInt(tokens[1]),
                                    new Product(
                                            Integer.parseInt(tokens[2]),
                                            Integer.parseInt(tokens[3])
                                    )
                            );

                        } catch (IndexOutOfBoundsException e) {
                            sb.append(indexOutOfBounds).append(System.lineSeparator());
                        }

                        break;
                }
            }
            Long toc = System.currentTimeMillis();
            System.out.println("Time elapsed: " + (toc - tic) + " ms");

        } catch (IOException e) {
            System.err.println("Exception caught: Input file could not be read.");
            System.exit(1);

        } catch (NumberFormatException e) {
            System.err.println("Exception caught: Inputs must be integer values.");

        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Exception caught: Incorrect input format.");

        }

        try {
            br.close();
        } catch (IOException e) {
            System.err.println("Exception caught: Input file could not be closed.");
            System.exit(1);
        }

        //OUTPUT
        try {
            bw.write(sb.toString());
        } catch (IOException e) {
            System.err.println("Exception caught: Output file could not be written.");
            System.exit(1);
        }

        try {
            bw.close();
        } catch (IOException e) {
            System.err.println("Exception caught: Output file could not be closed.");
            System.exit(1);
        }


    }
}
