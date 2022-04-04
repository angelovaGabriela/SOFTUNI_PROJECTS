package Google;
import java.util.ArrayList;
import java.util.List;
public class Person {
        private String name;
        private Company company;
        private List<Pokemon> pokemons;
        private List<Parent> parents;
        private List<Child> children;
        private Car car;

        public Person(String name) {
            this.name = name;
            this.pokemons = new ArrayList<>();
            this.parents = new ArrayList<>();
            this.children = new ArrayList<>();
        }

        public void setCompany(Company company) {
            this.company = company;
        }

        public void setCar(Car car) {
            this.car = car;
        }

        public void addPokemon(Pokemon pokemon) {
            this.pokemons.add(pokemon);
        }

        public void addParent(Parent parent) {
            this.parents.add(parent);
        }

        public void addChild(Child child) {
            this.children.add(child);
        }


        //{personName}
        //	Company:
        //	{companyName} {companyDepartment} {salary}
        //	...
        //	Children:
        //	{childName} {childBirthday}
        //	{childName} {childBirthday}
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(name).append("\n");
            sb.append("Company:").append("\n");
            if (company != null) {
                sb.append(company).append("\n");
            }
            sb.append("Car:").append("\n");
            if (car != null) {
                sb.append(car).append("\n");
            }
            sb.append("Pokemon:").append("\n");
            for (Pokemon pokemon : pokemons) {
                sb.append(pokemon.toString()).append("\n");
            }
            sb.append("Parents:").append("\n");
            for (Parent parent : parents) {
                sb.append(parent.toString()).append("\n");
            }
            sb.append("Children:").append("\n");
            for (Child child : children) {
                sb.append(child.toString()).append("\n");
            }
            return sb.toString();
        }
    }

