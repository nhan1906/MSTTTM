/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program.model;

import java.awt.Graphics;

/**
 *
 * @author James
 */
// Class chung cho Board game va nhan vat
public abstract class GameObject {
    private String name;
    public abstract void Draw(Graphics g);
}
