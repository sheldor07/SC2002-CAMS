/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package mainprogram;

import UI.Portal.Portal;

/**
 * The `CAMs` class serves as the entry point for the CAMs (Camp Administration Management System) application.
 * It initializes and starts the application's portal, allowing users to interact with the system.
 *
 * The `main` method within this class is the starting point of the CAMs application.
 *
 * @author weiya
 */
public class CAMs {
    public static void main(String[] args) throws Exception{
        Portal portal = new Portal();
        portal.start();
    }
}
