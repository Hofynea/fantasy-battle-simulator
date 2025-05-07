import org.junit.Test;

import static org.junit.Assert.*;

public class TDD {
    GamePlay game;

    @org.junit.Before
    public void setUp() throws Exception {
        game = new GamePlay();
    }


    // dealtDamage test cases
    // normal experience when healthy
    @Test
    public void dealtDamageNormalExperience() {
        Wizard wiz = new Wizard();
        Barbarian bar = new Barbarian();
        Bard bard = new Bard();
        Druid dru = new Druid();
        Ranger ran = new Ranger();
        Rogue ro = new Rogue();

        game.dealDamage(wiz);
        assertEquals("Wizard experience", wiz.experience, 5);

        game.dealDamage(bar);
        assertEquals("Barbarian experience", bar.experience, 10);

        game.dealDamage(bard);
        assertEquals("Bard experience", bard.experience, 6);

        game.dealDamage(dru);
        assertEquals("Druid experience", dru.experience, 7);

        game.dealDamage(ran);
        assertEquals("Ranger experience", ran.experience, 8);

        game.dealDamage(ro);
        assertEquals("Rogue experience", ro.experience, 5);
    }

    // double experience when health from 0-10
    @Test
    public void dealtDamageDoubleDamage() {
        int startExperience = 23;

        Barbarian bar1 = new Barbarian();
        bar1.health = 1;
        bar1.experience = startExperience;
        int triedDamage1 = game.dealDamage(bar1);
        assertEquals("Tried damage with 1 health", triedDamage1, bar1.damage*2);
        assertEquals("Barbarian experience with 1 health", bar1.experience, startExperience+bar1.damage*2);

        Barbarian bar9 = new Barbarian();
        bar9.health = 9;
        bar9.experience = startExperience;
        int triedDamage9 = game.dealDamage(bar9);
        assertEquals("Tried damage with 9 health", triedDamage9, bar9.damage*2);
        assertEquals("Barbarian experience with 9 health", bar9.experience, startExperience+bar9.damage*2);
    }

    // normal experience when health from 10-100
    @Test
    public void dealtDamageNormalFinalExperience() {
        int startExperience = 17;

        Barbarian bar10 = new Barbarian();
        bar10.health = 10;
        bar10.experience = startExperience;
        int triedDamage10 = game.dealDamage(bar10);
        assertEquals("Tried damage with 10 health", triedDamage10, bar10.damage);
        assertEquals("Barbarian experience with 10 health", bar10.experience, startExperience + bar10.damage);

        Barbarian bar11 = new Barbarian();
        bar11.health = 11;
        bar11.experience = startExperience;
        int triedDamage11 = game.dealDamage(bar11);
        assertEquals("Tried damage with 11 health", triedDamage11, bar11.damage);
        assertEquals("Barbarian experience with 11 health", bar11.experience, startExperience + bar11.damage);

        Barbarian bar99 = new Barbarian();
        bar99.health = 99;
        bar99.experience = startExperience;
        int triedDamage99 = game.dealDamage(bar99);
        assertEquals("Tried damage with 99 health", triedDamage99, bar99.damage);
        assertEquals("Barbarian experience with 99 health", bar99.experience, startExperience + bar99.damage);

        Barbarian bar100 = new Barbarian();
        bar100.health = 100;
        bar100.experience = startExperience;
        int triedDamage100 = game.dealDamage(bar100);
        assertEquals("Tried damage with 100 health", triedDamage100, bar100.damage);
        assertEquals("Barbarian experience with 100", bar100.experience, startExperience + bar100.damage);
    }

    // zero experience when health equals "zero"
    @Test
    public void dealtDamageZeroExperienceAndZeroDamage() {
        Barbarian bar0 = new Barbarian();
        bar0.health = 0;
        int triedDamage0 = game.dealDamage(bar0);
        assertEquals("Tried damage with 0 health", triedDamage0, 0);
        assertEquals("Barbarian experience with 0 health", bar0.experience, 0);
    }

    // Invalid experience when health is less than "zero" or 100+
    @Test
    public void dealtDamageInvalidExperience() {
        Barbarian barNeg2147483647 = new Barbarian();
        barNeg2147483647.health = -2147483647;
        int triedDamageNeg2147483647 = game.dealDamage(barNeg2147483647);
        assertEquals("Tried damage when health set to -2147483647", triedDamageNeg2147483647, -1); // Junit4 does not support assertThrows

        Barbarian barNeg1 = new Barbarian();
        barNeg1.health = -1;
        int triedDamageNeg1 = game.dealDamage(barNeg1);
        assertEquals("Tried damage when health set to -1", triedDamageNeg1, -1);

        Barbarian bar101 = new Barbarian();
        bar101.health = 101;
        int triedDamage101 = game.dealDamage(bar101);
        assertEquals("Tried damage when health set to 101", triedDamage101, -1);

        Barbarian bar2147483647 = new Barbarian();
        bar2147483647.health = 2147483647;
        int triedDamage2147483647 = game.dealDamage(bar2147483647);
        assertEquals("Tried damage when health set to triedDamage2147483647", triedDamage2147483647, -1);
    }

    // takeDamage() test cases
    // normal experience and health when healthy
    @Test
    public void takeDamageNormalExperienceAndHealth() {
        Wizard wiz = new Wizard();
        Barbarian bar = new Barbarian();
        Bard bard = new Bard();
        Druid dru = new Druid();
        Ranger ran = new Ranger();
        Rogue ro = new Rogue();

        game.takeDamage(wiz, 5);
        assertEquals("Wizard experience", wiz.experience, 2);
        assertEquals("Wizard health",wiz.health, 96);

        game.takeDamage(bar, 10);
        assertEquals("Barbarian experience", bar.experience, 0);
        assertEquals("Barbarian health", bar.health, 100);

        game.takeDamage(bard, 6);
        assertEquals("Barbarian experience", bard.experience, 1);
        assertEquals("Barbarian health", bard.health, 97);

        game.takeDamage(dru, 7);
        assertEquals("Druid experience", dru.experience, 1);
        assertEquals("Druid health", dru.health, 97);

        game.takeDamage(ran, 8);
        assertEquals("Ranger experience", ran.experience, 0);
        assertEquals("Ranger health", ran.health, 100);

        game.takeDamage(ro, 5);
        assertEquals("Rogue experience",ro.experience, 1);
        assertEquals("Rogue health", ro.health, 100);
    }

    // protection > character blowDamage
    @Test
    public void takeDamageProtectionHigherThanBlowDamage() {
        int initialHealth = 60;
        int blowDamage = 10;
        int initialExperience = 32;

        Barbarian bar = new Barbarian();
        bar.protection = 20;
        bar.health = initialHealth;
        bar.experience = initialExperience;

        int actualDamage = game.takeDamage(bar, blowDamage);

        assertEquals("Actual damage", actualDamage, 0);
        assertEquals("Barbarian health", bar.health, initialHealth+(bar.protection-blowDamage)/2);
        assertEquals("Barbarian experience", bar.experience, initialExperience+(bar.protection-blowDamage));
    }

    // protection <= blowDamage
    @Test
    public void takeDamageProtectionEqualsOrLowerThanBlowDamage() {
        int blowDamage = 20;
        int initialExperience = 32;

        Barbarian bar = new Barbarian();
        bar.experience = initialExperience;

        int actualDamage = game.takeDamage(bar, blowDamage);

        assertEquals("Actual damage", actualDamage, blowDamage - bar.protection);
        assertEquals("Barbarian health", bar.health, 100 - actualDamage);
        assertEquals("Barbarian experience", bar.experience, initialExperience+(blowDamage-bar.protection)/2);
    }

    // invalid when health is below "zero"
    @Test
    public void takeDamageInvalid() {
        Barbarian barNeg2147483647 = new Barbarian();
        barNeg2147483647.health = -2147483647;
        int actualDamageNeg2147483647 = game.takeDamage(barNeg2147483647, -2147483647);
        assertEquals("actual damage with health set to -2147483647", actualDamageNeg2147483647, -1); // Junit4 does not support assertThrows

        Barbarian barNeg1 = new Barbarian();
        barNeg1.health = -1;
        int actualDamageNeg1 = game.takeDamage(barNeg1, -1);
        assertEquals("actual damage with health set to -1", actualDamageNeg1, -1);

        Barbarian bar101 = new Barbarian();
        bar101.health = 101;
        int actualDamage101 = game.takeDamage(bar101, 101);
        assertEquals("actual damage with health set to 101", actualDamage101, -1);

        Barbarian bar2147483647 = new Barbarian();
        bar2147483647.health = 2147483647;
        int actualDamage2147483647 = game.takeDamage(bar2147483647, 2147483647);
        assertEquals("actual damage with health set to 2147483647", actualDamage2147483647, -1);
    }

    // attack() test cases
    // Case 1: No levelUp for character and opponent
    @Test
    public void attackNoLevelUp() {
        Barbarian bar = new Barbarian();
        Wizard wiz = new Wizard();
        game.attack(wiz, bar);
        // check Wizard state after attack() cycle
        assertEquals("Wizard health after attack", wiz.health, 91);
        assertEquals("Wizard experience after attack", wiz.experience, 9);
        // check Barbarian state after attack() cycle
        assertEquals("Barbarian health after attack", bar.health, 100);
        assertEquals("Barbarian experience after attack", bar.experience, 15);
    }

    // Case 2: Character LevelUp
    @Test
    public void attackCharacterLevelUp() {
        Ranger ran = new Ranger();
        ran.experience = 10; // Adjusted so the Ranger levels up during the 1st round
        Druid dru = new Druid();
        game.attack(ran, dru);
        // Check Ranger state
        assertEquals("Ranger health after attack", ran.health, 100);
        assertEquals("Ranger experience after attack", ran.experience, 22);
        // Ranger should have leveled up
        // check if levelUp() for Ranger worked correctly
        assertEquals("Ranger pointsPerlevel after levelUp", ran.pointsPerLevel, 30);
        assertEquals("Ranger damage after levelUp", ran.damage, 16);
        assertEquals("Ranger protection after levelUp", ran.protection, 11);
        assertEquals("Ranger speed after levelUp", ran.speed, 3.0, 0); // excepts double
        assertEquals("Ranger level after levelUp", ran.level, 2);
        // Check Druid state
        assertEquals("Druid health after attack", dru.health, 96);
        assertEquals("Druid experience after attack", dru.experience, 9);
        assertEquals("Druid level after attack", dru.level, 1);
    }

    // Case 3: Opponent LevelUp
    @Test
    public void attackOpponentLevelUp() {
        Rogue rog = new Rogue();
        Bard bar = new Bard();
        bar.experience = 11; // Adjusted so the Bard levels up during the 1st & 2nd rounds
        game.attack(rog, bar);
        // Check Rogue state
        assertEquals("Rogue health after attack", rog.health, 97);
        assertEquals("Rogue experience after attack", rog.experience, 6);
        assertEquals("Rogue level after attack", rog.level, 1);
        // Check Bard state
        assertEquals("Bard health after attack", bar.health, 100);
        assertEquals("Bard health after attack", bar.experience, 21);
        // Bard should have leveled up twice
        // If the difference by half is 0.5 we floor it since only speed can be floating point number.
        // It has to be rounded because current levelUp() implementation returns floating numbers
        // for Character attributes that are integer, except speed.
        assertEquals("Bard damage after levelUp", bar.damage, 13); // floor it
        assertEquals("Bard speed after levelUp", bar.speed, 5.5, 0); // excepts double
        assertEquals("Bard protection after levelUp", bar.protection, 6); // floor it
        assertEquals("Bard pointsPerLevel after levelUp", bar.pointsPerLevel, 40);
        assertEquals("Bard level after levelUp", bar.level, 3);
    }

    // Case 4: Extreme/Edge case when c.damage is HIGH and o.protection is LOW. Barbarian will destroy
    // Wizard, since Barbarian he is dealing double damage to Wizard, who cannot protect themselves since
    // their protection level is at minimal value.
    @Test
    public void attackDamageHighAndProtectionLow() {
        Barbarian bar = new Barbarian();
        bar.damage = 200;
        Wizard wiz = new Wizard();
        wiz.protection = 1;

        game.attack(bar, wiz);
        assertEquals("Barbarian health after attack", bar.health, 100);
        assertEquals("Barbarian experience after attack", bar.experience, 200);
        assertEquals("Wizard health after attack", wiz.health, 0);
        assertEquals("Wizard experience after attack", wiz.experience, 99);
    }

    // Case 5: Extreme/Edge case when c.damage is LOW and o.protection is HIGH. Barbarian will not
    // be able to deal any damage to the Wizard since Barbarian damage is extremely low, and Wizard's
    // protection is extremely high making it impossible for Barbarian to kill the Wizard.
    @Test
    public void attackDamageLowAndProtectionHigh() {
        Barbarian bar = new Barbarian();
        bar.damage = 1;
        Wizard wiz = new Wizard();
        wiz.protection = 200;
        game.attack(bar, wiz);
        assertEquals("Barbarian health after attack", bar.health, 80);
        assertEquals("Barbarian experience after attack", bar.experience, 11);
        assertEquals("Barbarian level after attack", bar.level, 1);

        assertEquals("Wizard health after attack", wiz.health, 100);
        assertEquals("Wizard experience after attack", wiz.experience, 229);
        assertEquals("Wizard protection after attack", wiz.protection, 205);
        assertEquals("Wizard damage after attack", wiz.damage, 30);
        assertEquals("Wizard speed after attack", wiz.speed, 10.0, 0);
        assertEquals("Wizard pointsPerLevel after attack", wiz.pointsPerLevel, 320);
        assertEquals("Wizard level after attack", wiz.level, 6);
    }

    // Case 6: Actual fight between a character w/ c.damage HIGH & c.protection LOW and
    // an opponent w/ o.damage LOW & o.protection HIGH
    @Test
    public void attackCharacterHighDamageLowProtectionAndOpponentLowDamageHighProtection() {
        Barbarian bar = new Barbarian();
        bar.damage = 200;
        bar.protection = 1;
        Wizard wiz = new Wizard();
        wiz.damage = 1;
        wiz.protection = 200;
        game.attack(wiz, bar);
        assertEquals("Barbarian health after attack", bar.health, 100);
        assertEquals("Barbarian experience after attack", bar.experience, 200);
        assertEquals("Wizard health after attack", wiz.health, 100);
        assertEquals("Wizard experience after attack", wiz.experience, 1);
    }

    // Case 7: Both levelUp
    @Test
    public void attackBothLevelUp() {
        Ranger ran = new Ranger();
        ran.experience = 10; // Adjusted so the Ranger levels up during 1st round
        Bard bar = new Bard();
        bar.experience = 11; // Adjusted so the Bard levels up during 1st round
        game.attack(ran, bar);

        // Check Ranger health
        assertEquals("Ranger health after attack", ran.health, 100);
        assertEquals("Ranger experience after attack", ran.experience, 20);
        // Ranger should have leveled up
        // check if levelUp() for Ranger worked correctly
        assertEquals("Ranger damage after levelUp", ran.damage, 16);
        assertEquals("Ranger speed after levelUp", ran.speed, 3.0, 0); // excepts double
        assertEquals("Ranger protection after levelUp", ran.protection, 11);
        assertEquals("Ranger pointsPerLevel after levelUp", ran.pointsPerLevel, 30);
        assertEquals("Ranger level after levelUp", ran.level, 2);

        // Check Bard state
        assertEquals("Bard health after attack", bar.health, 100);
        assertEquals("Bard experience after attack", bar.experience, 22);
        // check if levelUp() for Bard works correctly
        // If the difference by half is 0.5 we floor it since only speed can be floating point number.
        // It has to be rounded because current levelUp() implementation returns floating point numbers
        // for the Character attributes that are integer, except speed.
        assertEquals("Bard damage after levelUp", bar.damage, 13);
        assertEquals("Bard speed after levelUp", bar.speed, 5.5, 0); // excepts double
        assertEquals("Bard protection after levelUp", bar.protection, 6); // floor it
        assertEquals("Bard pointsPerLevel after levelUp", bar.pointsPerLevel, 40);
        assertEquals("Bard level after levelUp", bar.level, 3); // Bard leveled up to levelTwo during 1st round
    }
}
