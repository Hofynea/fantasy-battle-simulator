import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


/*

Node Coverage:

{1,2,3,5,6,7,8,9,10,14,17,19,3,5,6,10,11,12,13,14,17,19,3,22,23,24,25,26,27,28,22,31}

Edge Coverage:

1: {1,2,3,5,6,7,8,9,10,17,19,3,22,23,24,25,26,27,28,22,31}
2: {1,2,3,5,6,10,11,12,13,14,17,19,3,22,23,27,28,22,31}

 */

public class WhiteBoxGiven {


    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    //NodeCoverage
    @Test
    public void nodeCoverage() {
        /* We need a character and at least two opponents
         - one opponent that is slower than character
         - one opponent that is faster than character
         - one of the opponents health should be low enough for character to kill them

         since we cannot pass a list of opponents, customize the character parameters to
         visit all the nodes
        */
        Character dru = new Druid(); // character, speed 3, damage 60
        dru.damage = 60;

        GamePlay game = new GamePlay(dru);
        int expGained = game.play();

        // Check that only first 3 opponents survived
        assertEquals(3, game.opponents.size());

        // Check the first opponent
        assertEquals(3, game.opponents.get(0).level); // Wizard, level 3
        assertEquals(100, game.opponents.get(0).health); // Wizard, health 100
        assertEquals(36, game.opponents.get(0).experience); // Wizard, exp 36

        // Check the last surviving opponent
        assertEquals(4, game.opponents.get(2).level); // Druid, level 4
        assertEquals(100, game.opponents.get(2).health); // Druid, health 100
        assertEquals(75, game.opponents.get(2).experience); // Druid, exp 75

        // Check character
        assertEquals(612, expGained);
        assertEquals(612, dru.experience);
        assertEquals(100, dru.health);
        assertEquals(7, dru.level);
        assertEquals(960, dru.pointsPerLevel);
        assertEquals(120, dru.damage);
        assertEquals(16, dru.protection);
        assertEquals(4.5, dru.speed, 0);
    }

    @Test
    public void edgeCoverage1() {
        /* We need an opponent slower than the character who will also be
           killed by the character
        */
        Character dru = new Druid(); // character, speed 3, damage 110
        dru.damage = 110;
        Character ran = new Ranger(); // opponent, speed 2.5, protection 8

        GamePlay game = new GamePlay(dru, ran);
        int expGained = game.play();

        // Check that no opponents are left
        assertEquals(0, game.opponents.size());

        // Check the opponent: no health, no level, some experience
        assertEquals(1, ran.level);
        assertEquals(0, ran.health);
        assertEquals(51, ran.experience);

        // Check character: full health, few level ups, lots of experience
        assertEquals(111, expGained);
        assertEquals(111, dru.experience);
        assertEquals(100, dru.health);
        assertEquals(4, dru.level);
        assertEquals(120, dru.pointsPerLevel);
        assertEquals(140, dru.damage);
        assertEquals(10, dru.protection);
        assertEquals(3.75, dru.speed, 0);
    }

    @Test
    public void edgeCoverage2() {
        /* We need an opponent faster than character that is also not going
           to be killed by the character
        */
        Character dru = new Druid(); // character, speed 3, damage 7
        Character bard = new Bard(); // opponent, speed 4.5, health 100
        bard.pointsPerLevel = 40; // to prevent level up and simplify assertions

        GamePlay game = new GamePlay(dru, bard);
        int expGained = game.play();

        // Check that opponent survived
        assertEquals(1, game.opponents.size());

        // Check the opponent: some damage, no level up, some experience
        assertEquals(1, bard.level);
        assertEquals(96, bard.health);
        assertEquals(10, bard.experience);


        // Check character: some damage, no level up, some experience
        assertEquals(8, expGained);
        assertEquals(8, dru.experience);
        assertEquals(98, dru.health);
    }
}
