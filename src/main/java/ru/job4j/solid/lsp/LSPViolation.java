package ru.job4j.solid.lsp;

public class LSPViolation {

    public static class Dog {
        protected String name;
        protected int weight;

        public Dog(String name) {
            validate(name);
            this.name = name;
        }

        public void setName(String name) {
            validate(name);
            this.name = name;
        }

        private void validate(String name) {
            if (!name.matches("[a-zA-Z]{3,10}")) {
                throw new IllegalArgumentException("Not a valid name for dog");
            }
        }

        public void setWeight(int weight) {
            if (weight < 1) {
                throw new IllegalArgumentException("This is not a dog");
            }
            this.weight = weight;
        }

        public String bark() {
            return ("wow ").repeat(5);
        }
    }

    public static class SomeDog extends Dog {

        public SomeDog(String name) {
            super(name);
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public void setWeight(int weight) {
            if (weight > 5) {
                throw new IllegalArgumentException("This is not a some dog");
            }
            this.weight = weight;
        }

        @Override
        public String bark() {
            return "Hi guys, my name is " + name;
        }
    }

    public static void main(String[] args) throws Exception {
        Dog d1 = new Dog("Bobik");
        d1.setWeight(10);
        System.out.println(d1.bark());
        Dog d2 = new SomeDog("Sharik");
        d2.setName("Ivan Ivanovich");
        d2.setWeight(4);
        System.out.println(d2.bark());
    }
}
