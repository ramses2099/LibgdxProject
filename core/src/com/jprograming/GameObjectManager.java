package com.jprograming;

import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameObjectManager {

    List<GameObject> gameObjectList;

    public GameObjectManager() {
        gameObjectList = new ArrayList<GameObject>();
    }

    //
    public void addGameObject(GameObject gameObject) {
        this.gameObjectList.add(gameObject);
    }

    public void removeGameObject(GameObject gameObject) {
        this.gameObjectList.remove(gameObject);
    }

    public List<GameObject> getGameObjectList() {
        return this.gameObjectList;
    }

    public void update(float delta) {
        Iterator<GameObject>  listObject = this.gameObjectList.iterator();
        while(listObject.hasNext()){
            GameObject o = listObject.next();
            if(o.isVisible()){
                o.update(delta);
            }else{
                listObject.remove();
            }
        }
    }

    public void draw(Batch batch) {
        for(GameObject o: this.gameObjectList){
            if(o.isVisible()){
                o.draw(batch);
            }
        }
    }
}
