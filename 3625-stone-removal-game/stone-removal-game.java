class Solution {
    public boolean canAliceWin(int n) {

        int remove = 10;
        boolean aliceTurn = true;

        while (n >= remove) {
            n -= remove;

            remove--;

            aliceTurn = !aliceTurn;
        }

        // If it is Alice's turn and she cannot move,
        // Alice loses.
        // If it is Bob's turn and he cannot move,
        // Alice wins.
        return !aliceTurn;
    }
}