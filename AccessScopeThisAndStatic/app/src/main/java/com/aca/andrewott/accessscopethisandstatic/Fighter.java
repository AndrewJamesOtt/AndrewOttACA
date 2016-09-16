package com.aca.andrewott.accessscopethisandstatic;

import android.util.Log;

/**
 * Created by andrewott on 9/12/16.
 */

    public class Fighter extends AlienShip{

        public Fighter(){
            super(400);
            // Strong shields for a fighter
            Log.i("Location: ", "Fighter constructor");
        }

        public void fireWeapon(){
            Log.i("Firing weapon: ", "lasers firing");
        }

    }
