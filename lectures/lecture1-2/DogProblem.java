public class DogProblem {
    public static Dog[] largerThanFourNeighbours(Dog[] dogs){
        Dog[] returnDogs = new Dog[dogs.length];
        int cnt = 0;
        for (int i =0; i < dogs.length; i+=1){
            if (isBigggestOfFour(dogs,i)){
                cnt = cnt + 1;
            }
        }
        returnDogs = arrayWithNoNull(dogs, cnt);
        return returnDogs;
    }

    public static Dog[] arrayWithNoNull(Dog[] dogs, int cnt){
        Dog[] noNullDogs = new Dog[cnt];
        for (int i = 0; i < cnt; i += 1){
            noNullDogs[i] = dogs[i];
        }
        return noNullDogs;
    }

    public static boolean isBigggestOfFour(Dog[] dogs, int i){
        boolean isBiggest = true;
        for (int j = -2; j<= 2; j+=1){
            int compareIndex = i+j;
            if(j == 0){
                continue;
            }
            if (validIndex(dogs, compareIndex )) {
                if (dogs[compareIndex].weightInPounds >= dogs[i].weightInPounds){
                    isBiggest = false;
                }
            }
        }
        return isBiggest;
    }

    public static boolean validIndex(Dog[] dogs, int i){
        if (i < 0 || i >= dogs.length){
            return false;
        }
        return true;
    }
}
