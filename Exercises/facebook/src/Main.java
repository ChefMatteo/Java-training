public class Main {
    public static void main(String[] args) {
        User banana33 = new User("banana", "33");
        User complottista = new User("terra", "piatta");
        User giovanni1953 = new User("Giovanni", "Pasqualo");
        User ajeje = new User("Ajeje", "Brazorf");
        User atac = new User("Atac", "Roma");
        User romaninaRegna = new User("House", "Monique");

        Facebook.getFacebook().addPost(atac, "Multe a tutto spiano a chi non ha il biglietto!", 1);
        Facebook.getFacebook().searchPostById(1).addLike(ajeje);
        Facebook.getFacebook().searchPostById(1).addComment(ajeje, "bravi!");
        Facebook.getFacebook().searchPostById(1).addComment(complottista, "L'atac è controllata dai poteri forti degli zingari");
        Facebook.getFacebook().searchPostById(1).addComment(romaninaRegna, "cazo dici tu ti spaco testa");

        Facebook.getFacebook().addPost(complottista, "Uccidiamo il cincueGGI!!1", 2);
        Facebook.getFacebook().searchPostById(2).addLike(ajeje);
        Facebook.getFacebook().searchPostById(2).addComment(romaninaRegna, "dami a me 50 euro amazo io con bottilia");
        Facebook.getFacebook().searchPostById(2).addComment(giovanni1953, "Buongiornissimooo!!11 KaFFèèèee????");

        Facebook.getFacebook().addPost(banana33, "33 l'età non è", 3);
        Facebook.getFacebook().searchPostById(3).addLike(giovanni1953);
        Facebook.getFacebook().searchPostById(3).addComment(romaninaRegna, "e che è? pistola?");
        Facebook.getFacebook().searchPostById(3).addComment(complottista, "sarà un numero della massoneria");

        Facebook.getFacebook().newFriendship(atac, romaninaRegna);
        Facebook.getFacebook().newFriendship(atac, ajeje);
        Facebook.getFacebook().newFriendship(giovanni1953, ajeje);

        Facebook.getFacebook().removeFriendship(ajeje, atac);

        Facebook.getFacebook().commentsOfUser(complottista);








    }
}
