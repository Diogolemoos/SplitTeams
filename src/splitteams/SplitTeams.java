/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */


/* 
Github link: https://github.com/Diogolemoos/SplitTeams
*/
package splitteams;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author diogo
 */
public class SplitTeams {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<Person> allPeople = readDataFromFile("MOCK_DATA.csv");
        List<Team> teams = generateTeams(allPeople, 20, 5);

        for (Team team : teams) {
            System.out.println("Team: " + team.getName());
            for (Person member : team.getMembers()) {
                System.out.println(member.getId()+ " - " + member.getFirstName() + " " + member.getLastName () + " - email: " + member.getEmail());
            }
            System.out.println();
        }
    }

    private static List<Person> readDataFromFile(String filename) {
        List<Person> people = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0]);
                    String firstName = parts[1];
                    String lastName = parts[2];
                    String email = parts[3];
                    people.add(new Person(id, firstName, lastName, email));
                }
            }
        } catch (Exception e) {
            
        }
        return people;
    }

    private static List<Team> generateTeams(List<Person> people, int numTeams, int teamSize) {
        if (people.size() < numTeams * teamSize) {
            System.out.println("No more people to create the teams.");
            return Collections.emptyList();
        }

        Collections.shuffle(people);
        List<Team> teams = new ArrayList<>();
        List<Person> availablePeople = new ArrayList<>(people);

        for (int i = 1; i <= numTeams; i++) {
            Collections.shuffle(availablePeople);
            List<Person> teamMembers = new ArrayList<>(teamSize);

            for (int j = 0; j < teamSize; j++) {
                teamMembers.add(availablePeople.remove(0));
            }

            String teamName = "Team " + i;
            teams.add(new Team(teamName, teamMembers));
        }

        return teams;
    }
    }
    

