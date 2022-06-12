package com.mykola.hw2_android.mathExpressionParser;

public class MathExpressionParser {

    private static final double CONSTANT_VALUE[] = {
            Math.PI,
            Math.E,
            Math.sqrt(2),
            Math.sqrt(0.5),
            Math.log(2),
            Math.log(10),
            1.0 / Math.log(2),
            1.0 / Math.log(10)
    };

    private Node root = null;
    private byte[] expression;
    private double tokenValue;
    private OPERATOR operator;
    private int position;
    private double[] argument;
    private int arguments;

    private class Node {
        OPERATOR operator;
        double value;
        Node left, right;

        private void init(OPERATOR operator, double value, Node left) {
            this.operator = operator;
            this.value = value;
            this.left = left;
        }

        Node(OPERATOR operator, Node left) {
            init(operator, 0, left);
        }

        Node(OPERATOR operator) {
            init(operator, 0, null);
        }

        Node(OPERATOR operator, double value) {
            init(operator, value, null);
        }

        double calculate() throws Exception {
            double x;

            switch(operator) {
                case NUMBER:
                    return value;
                case PLUS:
                    return left.calculate() + right.calculate();
                case MINUS:
                    return left.calculate() - right.calculate();
                case MULTIPLY:
                    return left.calculate() * right.calculate();
                case DIVIDE:
                    return left.calculate() / right.calculate();
                case UNARY_MINUS:
                    return -left.calculate();
                case SIN:
                    return Math.sin(left.calculate());
                case COS:
                    return Math.cos(left.calculate());
                case TAN:
                    return Math.tan(left.calculate());
                case COT:
                    return 1 / Math.tan(left.calculate());
                case SEC:
                    return 1 / Math.cos(left.calculate());
                case CSC:
                    return 1 / Math.sin(left.calculate());
                case ASIN:
                    return Math.asin(left.calculate());
                case ACOS:
                    return Math.acos(left.calculate());
                case ATAN:
                    return Math.atan(left.calculate());
                case ACOT:
                    return Math.PI / 2 - Math.atan(left.calculate());
                case ASEC:
                    return Math.acos(1 / left.calculate());
                case ACSC:
                    return Math.asin(1 / left.calculate());
                case SINH:
                    x = left.calculate();
                    return (Math.exp(x) - Math.exp(-x)) / 2;
                case COSH:
                    x = left.calculate();
                    return (Math.exp(x) + Math.exp(-x)) / 2;
                case TANH:
                    x = left.calculate();
                    return (Math.exp(2 * x) - 1) / (Math.exp(2 * x) + 1);
                case COTH:
                    x = left.calculate();
                    return (Math.exp(2 * x) + 1) / (Math.exp(2 * x) - 1);
                case SECH:
                    x = left.calculate();
                    return 2 / (Math.exp(x) + Math.exp(-x));
                case CSCH:
                    x = left.calculate();
                    return 2 / (Math.exp(x) - Math.exp(-x));
                case ASINH:
                    x = left.calculate();
                    return Math.log(x + Math.sqrt(x * x + 1));
                case ACOSH:
                    x = left.calculate();
                    return Math.log(x + Math.sqrt(x * x - 1));
                case ATANH:
                    x = left.calculate();
                    return Math.log((1 + x) / (1 - x)) / 2;
                case ACOTH:
                    x = left.calculate();
                    return Math.log((x + 1) / (x - 1)) / 2;
                case ASECH:
                    x = left.calculate();
                    return Math.log((1 + Math.sqrt(1 - x * x)) / x);
                case ACSCH:
                    x = left.calculate();
                    return Math.log(1 / x + Math.sqrt(1 + x * x) / Math.abs(x));
                case RANDOM:
                    return Math.random();
                case CEIL:
                    return Math.ceil(left.calculate());
                case FLOOR:
                    return Math.floor(left.calculate());
                case ROUND:
                    return Math.round(left.calculate());
                case ABS:
                    return Math.abs(left.calculate());
                case EXP:
                    return Math.exp(left.calculate());
                case LOG:
                    return Math.log(left.calculate());
                case SQRT:
                    return Math.sqrt(left.calculate());
                case POW:
                    return Math.pow(left.calculate(), right.calculate());
                case ATAN2:
                    return Math.atan2(left.calculate(), right.calculate());
                case MIN:
                    return Math.min(left.calculate(), right.calculate());
                case MAX:
                    return Math.max(left.calculate(), right.calculate());
                case X:
                    return argument[(int) value];
                case FACTORIAL:
                    return MathHelpers.factorial(String.valueOf(left.calculate()));
                default:
                    throw new Exception("Помилка обчислення вузла");
            }
        }
    }

    private boolean isLetter() {
        return Character.isLetter(expression[position]);
    }

    private boolean isDigit() {
        return Character.isDigit(expression[position]);
    }

    private boolean isPoint() {
        return expression[position] == '.';
    }

    private boolean isFunctionSymbol() {
        byte c = expression[position];
        return Character.isLetterOrDigit(c) || c == '_';
    }

    private void getToken() throws Exception {
        int i;

        if (position == expression.length - 1) {
            operator = OPERATOR.END;
        } else if ((i = "+-*/()[]{},".indexOf(expression[position])) != -1) {
            position++;
            operator = OPERATOR.values()[i];
        } else if (isLetter()) {
            for (i = position++; isFunctionSymbol(); position++) ;
            String token = new String(expression, i, position - i);

            try {
                if (token.charAt(0) == 'X' && token.length() == 1) {
                    throw new Exception("Невідоме ключове слово");
                } else if (token.charAt(0) == 'X' && token.length() > 1 &&
                        Character.isDigit(token.charAt(1))) {
                    i = Integer.parseInt(token.substring(1));

                    if (i < 0) {
                        throw new Exception("Індекс 'x' має бути цілим невід'ємним числом");
                    }

                    if (arguments < i + 1) {
                        arguments = i + 1;
                    }

                    operator = OPERATOR.X;
                    tokenValue = i;
                } else {
                    operator = OPERATOR.valueOf(token);
                    i = operator.ordinal();

                    if (i < OPERATOR.SIN.ordinal() || i > OPERATOR.MAX.ordinal()) {
                        throw new IllegalArgumentException();
                    }
                }
            } catch (IllegalArgumentException _ex) {
                try {
                    tokenValue = CONSTANT_VALUE[CONSTANT_NAME.valueOf(token).ordinal()];
                    operator = OPERATOR.NUMBER;
                } catch (IllegalArgumentException ex) {
                    throw new Exception("Невідоме ключове слово");
                }
            }
        } else if (isDigit() || isPoint()) {
            for (i = position++; isDigit() || isPoint() || expression[position] == 'E'
                    || expression[position - 1] == 'E' && "+-".indexOf(expression[position]) != -1; position++) ;

            tokenValue = Double.parseDouble(new String(expression, i, position - i));
            operator = OPERATOR.NUMBER;
        } else {
            throw new Exception("Невідомий символ");
        }
    }

    public void compile(String expression) throws Exception {
        position = 0;
        arguments = 0;
        String s = expression.toUpperCase();

        String from[] = {" ", "\t"};

        for (int i = 0; i < from.length; i++) {
            s = s.replace(from[i], "");
        }

        this.expression = (s + '\0').getBytes();

        getToken();

        if (operator == OPERATOR.END) {
            throw new Exception("Несподіване закінчення виразу");
        }

        root = parse();

        if (operator != OPERATOR.END) {
            throw new Exception("Очікуване закінчення виразу");
        }
    }

    private Node parse() throws Exception {
        Node node = parse1();

        while (operator == OPERATOR.PLUS || operator == OPERATOR.MINUS) {
            node = new Node(operator, node);
            getToken();

            if (operator == OPERATOR.PLUS || operator == OPERATOR.MINUS) {
                throw new Exception("Два оператора поспіль");
            }
            node.right = parse1();
        }

        return node;
    }

    private Node parse1() throws Exception {
        Node node = parse2();

        while (operator == OPERATOR.MULTIPLY || operator == OPERATOR.DIVIDE) {
            node = new Node(operator, node);
            getToken();
            if (operator == OPERATOR.PLUS || operator == OPERATOR.MINUS) {
                throw new Exception("Два оператора поспіль");
            }
            node.right = parse2();
        }
        return node;
    }

    private Node parse2() throws Exception {
        Node node;
        if (operator == OPERATOR.MINUS) {
            getToken();

            node = new Node(OPERATOR.UNARY_MINUS, parse3());
        } else {
            if (operator == OPERATOR.PLUS) {
                getToken();
            }
            node = parse3();
        }
        return node;
    }

    private Node parse3() throws Exception {
        Node node;
        OPERATOR open;

        if (operator.ordinal() >= OPERATOR.SIN.ordinal() && operator.ordinal() <= OPERATOR.MAX.ordinal()) {

            int arguments;

            if (operator.ordinal() >= OPERATOR.POW.ordinal() && operator.ordinal() <= OPERATOR.MAX.ordinal()) {
                arguments = 2;
            } else {
                arguments = operator == OPERATOR.RANDOM ? 0 : 1;
            }

            node = new Node(operator);

            getToken();
            open = operator;
            if (operator != OPERATOR.LEFT_BRACKET && operator != OPERATOR.LEFT_SQUARE_BRACKET && operator != OPERATOR.LEFT_CURLY_BRACKET) {
                throw new Exception("Очікується відкрита дужка");
            }
            getToken();

            if (arguments > 0) {
                node.left = parse();

                if (arguments == 2) {
                    if (operator != OPERATOR.COMMA) {
                        throw new Exception("Очікується кома");
                    }
                    getToken();
                    node.right = parse();
                }
            }
            checkBracketBalance(open);
        } else {
            switch (operator) {
                case X:
                case NUMBER:
                    node = new Node(operator, tokenValue);
                    break;
                case LEFT_BRACKET:
                case LEFT_SQUARE_BRACKET:
                case LEFT_CURLY_BRACKET:
                    open = operator;
                    getToken();
                    node = parse();
                    checkBracketBalance(open);
                    break;
                default:
                    throw new Exception("несподіваний оператор");
            }
        }
        getToken();
        return node;
    }

    private void checkBracketBalance(OPERATOR open) throws Exception {
        if (open == OPERATOR.LEFT_BRACKET && operator != OPERATOR.RIGHT_BRACKET ||
                open == OPERATOR.LEFT_SQUARE_BRACKET && operator != OPERATOR.RIGHT_SQUARE_BRACKET ||
                open == OPERATOR.LEFT_CURLY_BRACKET && operator != OPERATOR.RIGHT_CURLY_BRACKET) {
            throw new Exception("Очікується закрита дужка або інший тип закритої дужки");
        }
    }

    public double calculate(double[] x) throws Exception {
        this.argument = x;
        return calculate();
    }

    public double calculate() throws Exception {
        if (root == null) {
            throw new Exception("\n\tВикористання calculate() без compile()\n");
        }

        int length = argument == null ? 0 : argument.length;

        if (length != arguments) {
            throw new Exception("\n\tНедійсна кількість аргументів виразу\n");
        }

        return root.calculate();
    }

    public int getArguments() {
        return arguments;
    }

    public static String calculate(String s) {
        MathExpressionParser parser = new MathExpressionParser();
        double result = 0.0;

        try {
            parser.compile(s);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        parser.argument = null;

        try {
            result = parser.calculate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return Double.toString(result);
    }
}
