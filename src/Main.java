class Animal {
    String family;
    String name;
    int age;
    boolean isMammal;

    // Constructeur paramétré pour la classe Animal
    public Animal(String family, String name, int age, boolean isMammal) {
        this.family = family;
        this.name = name;
        this.age = age;
        this.isMammal = isMammal;
    }
}

class Zoo {
    Animal[] animals;
    String name;
    String city;
    final int nbrCages = 25; // Instruction 14 : Rendre nbrCages une constante
    int animalCount; // Compteur d'animaux actuellement dans le zoo

    // Constructeur paramétré pour la classe Zoo
    public Zoo(String name, String city) { // Ne pas passer nbrCages comme paramètre
        this.name = name;
        this.city = city;
        this.animals = new Animal[nbrCages];
        this.animalCount = 0;
    }

    // Méthode pour ajouter un animal au zoo
    public boolean addAnimal(Animal animal) {
        if (animalCount < nbrCages) {
            animals[animalCount] = animal;
            animalCount++;
            return true;
        }
        return false; // Impossible d'ajouter plus d'animaux que de cages disponibles
    }

    // Méthode pour afficher les informations du zoo
    public void displayZoo() {
        System.out.println("Nom du zoo : " + name);
        System.out.println("Ville : " + city);
        System.out.println("Nombre de cages disponibles : " + (nbrCages - animalCount));
    }

    // Méthode pour afficher les animaux du zoo
    public void displayAnimals() {
        for (int i = 0; i < animalCount; i++) {
            System.out.println("Animal " + (i + 1) + " : " + animals[i].name);
        }
    }

    // Méthode pour chercher un animal par son nom et retourner son indice
    public int searchAnimal(String animalName) {
        for (int i = 0; i < animalCount; i++) {
            if (animals[i].name.equals(animalName)) {
                return i;
            }
        }
        return -1; // Animal non trouvé
    }

    // Méthode pour supprimer un animal et renvoyer true si la suppression a réussi
    public boolean removeAnimal(Animal animal) {
        int index = -1;
        for (int i = 0; i < animalCount; i++) {
            if (animals[i] == animal) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            // Supprimer l'animal et déplacer les autres vers l'indice libre
            for (int i = index; i < animalCount - 1; i++) {
                animals[i] = animals[i + 1];
            }
            animals[animalCount - 1] = null; // Libérer la dernière case
            animalCount--;
            return true;
        }
        return false; // Animal non trouvé ou suppression impossible
    }

    // Instruction 15 : Méthode statique pour comparer deux zoos et renvoyer le zoo avec le plus d'animaux
    public static Zoo comparerZoo(Zoo z1, Zoo z2) {
        if (z1.animalCount > z2.animalCount) {
            return z1;
        } else if (z2.animalCount > z1.animalCount) {
            return z2;
        } else {
            // Si les deux zoos ont le même nombre d'animaux, vous pouvez choisir l'un d'entre eux
            return z1; // Par exemple, renvoyer z1 en cas d'égalité
        }
    }

    // Instruction 16 : Méthode statique pour vérifier si le zoo est plein
    public static boolean isZooFull(Zoo zoo) {
        return zoo.animalCount == zoo.nbrCages;
    }
}

public class Main {
    public static void main(String[] args) {
        // Création d'un zoo
        Zoo myZoo = new Zoo("Mon Zoo", "Ma Ville");

        // Création d'animaux avec le constructeur paramétré
        Animal lion = new Animal("Félin", "Lion", 5, true);
        Animal elephant = new Animal("Éléphant", "Babar", 10, true);
        Animal giraffe = new Animal("Girafe", "Sophie", 7, true);

        // Ajouter des animaux au zoo
        myZoo.addAnimal(lion);
        myZoo.addAnimal(elephant);
        myZoo.addAnimal(giraffe);

        // Afficher les informations du zoo
        myZoo.displayZoo();

        // Afficher les animaux du zoo
        myZoo.displayAnimals();

        // Rechercher un animal par son nom
        int index = myZoo.searchAnimal("Babar");
        if (index != -1) {
            System.out.println("L'animal Babar a été trouvé à l'indice " + index);
        } else {
            System.out.println("L'animal Babar n'a pas été trouvé dans le zoo.");
        }

        // Supprimer un animal du zoo
        boolean removed = myZoo.removeAnimal(elephant);
        if (removed) {
            System.out.println("L'animal Babar a été supprimé du zoo.");
        } else {
            System.out.println("L'animal Babar n'a pas été trouvé dans le zoo.");
        }

        // Utiliser la méthode statique comparerZoo
        Zoo zooA = new Zoo("Zoo A", "Ville A");
        Zoo zooB = new Zoo("Zoo B", "Ville B");
        zooA.addAnimal(new Animal("Félin", "Tigre", 4, true));
        zooB.addAnimal(new Animal("Oiseau", "Aigle", 3, false));

        Zoo zooAvecLePlusDAanimaux = Zoo.comparerZoo(zooA, zooB);
        System.out.println("Le zoo avec le plus d'animaux est : " + zooAvecLePlusDAanimaux.name);

        // Utiliser la méthode statique isZooFull
        System.out.println("Le zoo est-il plein ? " + Zoo.isZooFull(myZoo));
    }
}
