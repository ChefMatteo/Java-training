public class main {
    public static void main(String[] args) {
        TinderLike tinderLike = new TinderLike();
        tinderLike.addUser(new User("ajeje", "a@.com","123456" ));
        tinderLike.addUser(new User("brazorf", "b@.com","223456" ));
        tinderLike.addUser(new User("aldo", "c@.com","323456" ));
        tinderLike.addUser(new User("giovanni", "d@.com","423456" ));
        tinderLike.addUser(new User("giacomo", "e@.com","523456" ));


        //a ABCDE, b ABDE, c A, d ADE, e AE
        tinderLike.setInterest(new Interest("A"));
        tinderLike.setInterest(new Interest("B"));
        tinderLike.setInterest(new Interest("C"));
        tinderLike.setInterest(new Interest("D"));
        tinderLike.setInterest(new Interest("E"));

        tinderLike.addInterest("a@.com", tinderLike.getInterestByName("A"));
        tinderLike.addInterest("a@.com", tinderLike.getInterestByName("C"));
        tinderLike.addInterest("a@.com", tinderLike.getInterestByName("D"));
        tinderLike.addInterest("a@.com", tinderLike.getInterestByName("B"));
        tinderLike.addInterest("a@.com", tinderLike.getInterestByName("E"));

        tinderLike.addInterest("b@.com", tinderLike.getInterestByName("B"));
        tinderLike.addInterest("b@.com", tinderLike.getInterestByName("D"));
        tinderLike.addInterest("b@.com", tinderLike.getInterestByName("A"));
        tinderLike.addInterest("b@.com", tinderLike.getInterestByName("E"));

        tinderLike.addInterest("c@.com", tinderLike.getInterestByName("A"));

        tinderLike.addInterest("d@.com", tinderLike.getInterestByName("D"));
        tinderLike.addInterest("d@.com", tinderLike.getInterestByName("E"));
        tinderLike.addInterest("d@.com", tinderLike.getInterestByName("A"));

        tinderLike.addInterest("e@.com", tinderLike.getInterestByName("A"));
        tinderLike.addInterest("e@.com", tinderLike.getInterestByName("E"));

        System.out.println(tinderLike.userTreeSet.toString());

    }
}
