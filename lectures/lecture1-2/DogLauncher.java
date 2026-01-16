public class DogLauncher {
    public static void main (String[] args){
        Dog[] dogs = new Dog[2];
        dogs[0] = new Dog(8);
        dogs[1] = new Dog(35);
        dogs[0].makeNoise(); /** non-static method */
        Dog bigger = Dog.maxDog(dogs[0],dogs[1]);
        bigger.makeNoise();

        Dog[] dogs1 = new Dog[]{
                new Dog(10),
                new Dog(15),
                new Dog(20),
                new Dog(12),
                new Dog(18),
                new Dog(40),
                new Dog(25),
                new Dog(3),
        };
        Dog[] bigDogs1 = DogProblem.largerThanFourNeighbours(dogs1);
        for(int i = 0; i < bigDogs1.length; i += 1){
            System.out.println(bigDogs1[i].weightInPounds + " ");
        }
        System.out.println();
    }
}