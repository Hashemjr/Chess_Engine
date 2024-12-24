package com.chess.engine.board;
import com.chess.engine.pieces.Piece;
import java.util.Map;
import java.util.HashMap;
import com.google.common.collect.ImmutableMap;

public abstract class Tile {

    protected final int tileCoordinate;     // member field representing tile number

    private static final Map<Integer, EmptyTile> EMPTY_TILES = createAllPossibleEmptyTiles();


    // Every possible empty tile that could ever exist I have created upfront
    private static Map<Integer,EmptyTile> createAllPossibleEmptyTiles(){
        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();

        for(int i=0; i<64; i++){
            emptyTileMap.put(i,new EmptyTile(i));
        }

        return ImmutableMap.copyOf(emptyTileMap);
    }

    // factory method
    public static Tile createTile(final int tileCoordinate, final Piece piece){
        return piece != null ? new OccupiedTile(tileCoordinate,piece) : EMPTY_TILES.get(tileCoordinate);
    }

    private Tile(int tileCoordinate){
        this.tileCoordinate = tileCoordinate;
    }

    public abstract boolean isTileOccupied();
    public abstract Piece getPiece();           // get piece of a specific tile

    // subclass
    public static final class EmptyTile extends Tile{

        public EmptyTile(final int coordinate){
            super(coordinate);
        }

        @Override
        public boolean isTileOccupied(){
            return false;
        }

        @Override
        public Piece getPiece(){
            return null;
        }
    }

    // subclass
    public static final class OccupiedTile extends Tile{
        private final Piece pieceOnTile;

        public OccupiedTile(int coordinate, Piece pieceOnTile){
            super(coordinate);
            this.pieceOnTile = pieceOnTile;
        }

        @Override
        public boolean isTileOccupied(){
            return true;
        }

        @Override
        public Piece getPiece(){
            return this.pieceOnTile;
        }


    }

}
