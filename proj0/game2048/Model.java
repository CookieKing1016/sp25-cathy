package game2048;

import java.util.Formatter;
import java.util.Observable;


/** The state of a game of 2048.
 *  @author TODO: Cathy
 */
public class Model extends Observable {
    /** Current contents of the board. */
    private Board board;
    /** Current score. */
    private int score;
    /** Maximum score so far.  Updated when game ends. */
    private int maxScore;
    /** True iff game is ended. */
    private boolean gameOver;

    /* Coordinate System: column C, row R of the board (where row 0,
     * column 0 is the lower-left corner of the board) will correspond
     * to board.tile(c, r).  Be careful! It works like (x, y) coordinates.
     */

    /** Largest piece value. */
    public static final int MAX_PIECE = 2048;

    /** A new 2048 game on a board of size SIZE with no pieces
     *  and score 0. */
    public Model(int size) {
        System.out.println("Model 被成功创建了！");
        board = new Board(size);
        score = maxScore = 0;
        gameOver = false;
    }

    /** A new 2048 game where RAWVALUES contain the values of the tiles
     * (0 if null). VALUES is indexed by (row, col) with (0, 0) corresponding
     * to the bottom-left corner. Used for testing purposes. */
    public Model(int[][] rawValues, int score, int maxScore, boolean gameOver) {
        int size = rawValues.length;
        board = new Board(rawValues, score);
        this.score = score;
        this.maxScore = maxScore;
        this.gameOver = gameOver;
    }

    /** Return the current Tile at (COL, ROW), where 0 <= ROW < size(),
     *  0 <= COL < size(). Returns null if there is no tile there.
     *  Used for testing. Should be deprecated and removed.
     *  */
    public Tile tile(int col, int row) {
        return board.tile(col, row);
    }

    /** Return the number of squares on one side of the board.
     *  Used for testing. Should be deprecated and removed. */
    public int size() {
        return board.size();
    }

    /** Return true iff the game is over (there are no moves, or
     *  there is a tile with value 2048 on the board). */
    public boolean gameOver() {
        checkGameOver();
        if (gameOver) {
            maxScore = Math.max(score, maxScore);
        }
        return gameOver;
    }

    /** Return the current score. */
    public int score() {
        return score;
    }

    /** Return the current maximum game score (updated at end of game). */
    public int maxScore() {
        return maxScore;
    }

    /** Clear the board to empty and reset the score. */
    public void clear() {
        score = 0;
        gameOver = false;
        board.clear();
        setChanged();
    }

    /** Add TILE to the board. There must be no Tile currently at the
     *  same position. */
    public void addTile(Tile tile) {
        board.addTile(tile);
        checkGameOver();
        setChanged();
    }

    /** Tilt the board toward SIDE. Return true iff this changes the board.
     *
     * 1. If two Tile objects are adjacent in the direction of motion and have
     *    the same value, they are merged into one Tile of twice the original
     *    value and that new value is added to the score instance variable
     * 2. A tile that is the result of a merge will not merge again on that
     *    tilt. So each move, every tile will only ever be part of at most one
     *    merge (perhaps zero).
     * 3. When three adjacent tiles in the direction of motion have the same
     *    value, then the leading two tiles in the direction of motion merge,
     *    and the trailing tile does not.
     * */
    public boolean tilt(Side side) {
        System.out.println("Tilt 被调用了，方向是: " + side);
        boolean changed;
        changed = false;
        board.setViewingPerspective(side);
        /** c代表列，r代表行*/
        for(int c = 0;c < board.size(); c+=1 ){
            int lastMergedRow = -1;
            for(int r = 2;r >= 0; r-=1){
                Tile t = board.tile(c, r);
                if (t == null) continue; // 没方块就跳过
                int targetRow = r;
                for (int nextR = r+1; nextR < board.size(); nextR+=1){
                    Tile nextT = board.tile(c,nextR);
                    if (board.tile(c,nextR) == null){
                        targetRow = nextR;
                    }else if (nextT.value() == t.value() && nextR != lastMergedRow) {
                        // 情况2：数值相同，且对方没合并过。这就是最终目的地（合并）！
                        targetRow = nextR;
                        lastMergedRow = nextR; // 标记这一行已经合并过了
                        break; // 确定目的地，跳出探测循环
                    } else {
                        // 情况3：数值不同，或者对方合并过了，目的地就是当前 targetRow (上一个空格)
                        break;
                    }
                }
                if (targetRow != r) {
                    if (board.move(c, targetRow, t)) {
                        score += board.tile(c, targetRow).value();
                    }
                    changed = true;
                }
            }
        }
        board.setViewingPerspective(Side.NORTH);
        checkGameOver();
        if (changed) {
            setChanged();
        }
        return changed;
    }

    /** Checks if the game is over and sets the gameOver variable
     *  appropriately.
     */
    private void checkGameOver() {
        gameOver = checkGameOver(board);
    }

    /** Determine whether game is over. */
    private static boolean checkGameOver(Board b) {
        return maxTileExists(b) || !atLeastOneMoveExists(b);
    }

    /** Returns true if at least one space on the Board is empty.
     *  Empty spaces are stored as null.
     * */
    public static boolean emptySpaceExists(Board b) {
        boolean ifEmptySpace = false;
        for(int i = 0;i < 4; i += 1){
            for(int j = 0; j < 4; j += 1){
                if(b.tile(i,j) == null){
                    ifEmptySpace = true;
                }

            }
        }
        return ifEmptySpace;
    }

    /**
     * Returns true if any tile is equal to the maximum valid value.
     * Maximum valid value is given by MAX_PIECE. Note that
     * given a Tile object t, we get its value with t.value().
     */
    public static boolean maxTileExists(Board b) {
        boolean ifMaxTile = false;
        for(int i = 0;i < 4; i += 1){
            for(int j = 0; j < 4; j += 1){
                if(b.tile(i,j)!= null && b.tile(i,j).value() == MAX_PIECE){
                    ifMaxTile = true;
                }
            }
        }
        return ifMaxTile;
    }

    public static boolean isNull(Board b, int i, int j){
        boolean ifNull = true;
        if(b.tile(i,j) != null){
            ifNull = false;
        }
        return ifNull;
    }
    /**
     * Returns true if there are any valid moves on the board.
     * There are two ways that there can be valid moves:
     * 1. There is at least one empty space on the board.
     * 2. There are two adjacent tiles with the same value.
     */
    public static boolean atLeastOneMoveExists(Board b) {
        boolean ifAtLeastOneMoveExists = false;
        if (emptySpaceExists(b) || adjacentTilesExist(b)){
            ifAtLeastOneMoveExists = true;
        }
        return ifAtLeastOneMoveExists;
    }
    public static boolean adjacentTilesExist(Board b){
        int[] adjacent_i = {1,-1,0,0};
        int[] adjacent_j = {0,0,1,-1};
        boolean sameAdjacentValue = false;
        for(int i = 0;i < 4; i += 1){
            for(int j = 0; j < 4; j += 1){
                if(isNull(b,i,j)) {
                    continue;
                }
                for(int index = 0; index < 4;index +=1){
                    if(i+adjacent_i[index] <= -1 || i+adjacent_i[index] >= 4|| j+adjacent_j[index] <= -1|| j+adjacent_j[index] >= 4) {
                        continue;
                    }
                    if (isNull(b,i+adjacent_i[index],j+adjacent_j[index])){
                        continue;
                    }
                    if (b.tile(i, j).value() == b.tile(i + adjacent_i[index], j + adjacent_j[index]).value()){
                        sameAdjacentValue = true;
                    }
                }
            }
        }
        return sameAdjacentValue;
    }



    @Override
     /** Returns the model as a string, used for debugging. */
    public String toString() {
        Formatter out = new Formatter();
        out.format("%n[%n");
        for (int row = size() - 1; row >= 0; row -= 1) {
            for (int col = 0; col < size(); col += 1) {
                if (tile(col, row) == null) {
                    out.format("|    ");
                } else {
                    out.format("|%4d", tile(col, row).value());
                }
            }
            out.format("|%n");
        }
        String over = gameOver() ? "over" : "not over";
        out.format("] %d (max: %d) (game is %s) %n", score(), maxScore(), over);
        return out.toString();
    }

    @Override
    /** Returns whether two models are equal. */
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (getClass() != o.getClass()) {
            return false;
        } else {
            return toString().equals(o.toString());
        }
    }

    @Override
    /** Returns hash code of Model’s string. */
    public int hashCode() {
        return toString().hashCode();
    }
}
