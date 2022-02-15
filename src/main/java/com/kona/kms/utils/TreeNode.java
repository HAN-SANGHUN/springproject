package com.kona.kms.utils;

/**
 * Reimplementation of the java.swing.TreeNode interface with fewer methods
 *  
 * @author Andreas Schwier (info@cardcontact.de)
 */
public interface TreeNode {
    
	/**
	 * Return child at position childIndex
	 * 
	 * @param childIndex
	 * @return Child node
	 */
	public TreeNode getChildAt(int childIndex);
    
    
    
	/**
	 * Return number of childs
	 *  
	 * @return Number of childs
	 */
	public int getChildCount();

    
    
	/**
	 * Get index for child
	 * 
	 * @param child to look for
	 * @return Child index or -1
	 */
	public int getIndex(TreeNode child);
    
    
    
	/**
	 * Get parent for child
	 * 
	 * @return Parent or null if root or unknown
	 */
	public TreeNode getParent();

    
    
	/**
	 * Return true, if node is a leaf node
	 * 
	 * @return Node is leaf node
	 */
	public boolean isLeaf();
}
