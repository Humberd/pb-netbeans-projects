import java.io.FileNotFoundException;
import java.io.PrintWriter;


public class AvlTree {
	protected AvlNode root;
	private int rotations;
	private int comp;
	
	public void insert (String k, String[] s){
		AvlNode n = new AvlNode(k);
		insertAVL(this.root,n);
		n.translate = s;
	}
	private void insertAVL(AvlNode p, AvlNode q){
		if (p==null)
			this.root=q;
		else {
			comp++;
			if (q.key.compareTo(p.key)<0){
				if (p.left == null){
					p.left = q;
					q.parent = p;
					recursiveBalance(p);
				} else 
					insertAVL(p.left,q);
			} else if (q.key.compareTo(p.key)>0){
				if (p.right == null){
					p.right = q;
					q.parent =p;
					recursiveBalance(p);
				} else
					insertAVL(p.right,q);
			} 
		}
	}
	private void recursiveBalance(AvlNode cur){
		setBalance(cur);
		int balance = cur.balance;
		
		if (balance == -2){
			if(height(cur.left.left)>=height(cur.left.right))
				cur = rotateRight(cur);
			else
				cur = doubleRotateLeftRight(cur);
		} else if (balance == 2){
			if(height(cur.right.right)>=height(cur.right.left))
				cur = rotateLeft(cur);
			else
				cur = doubleRotateRightLeft(cur);
		}
		if (cur.parent != null)
			recursiveBalance(cur.parent);
		else {
			this.root = cur;
		}
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////
	private AvlNode rotateLeft(AvlNode n){		/////////////ROTACJA W LEWO
		AvlNode v = n.right;
		v.parent = n.parent;
		n.right = v.left;
		
		if (n.right!=null)
			n.right.parent=n;
		
		v.left = n;
		n.parent = v;
		
		if (v.parent!=null){
			if (v.parent.right==n)
				v.parent.right = v;
			else if (v.parent.left==n){
				v.parent.left =v;
			}
		}
		setBalance(n);
		setBalance(v);
		//System.out.println("____________rotateLeft___________");
		rotations++;
		return v;
	}
	private AvlNode rotateRight(AvlNode n){		///////////////ROTACJA W PRAWO
		AvlNode v = n.left;
		v.parent = n.parent;
		n.left=v.right;
		
		if (n.left!=null)
			n.left.parent=n;
		
		v.right =n;
		n.parent =v;
		
		if (v.parent!=null){
			if (v.parent.right==n)
				v.parent.right =v;
			else if (v.parent.left == n){
				v.parent.left=v;
			}
		}
		setBalance(n);
		setBalance(v);
		//System.out.println("____________rotateRight___________");
		rotations++;
		return v;
	}
	private AvlNode doubleRotateLeftRight(AvlNode n){		////////////ROTACJA W LEWO PRAWO
		//System.out.println("____________doubleRotateLeftRight__________");
		n.left = rotateLeft(n.left);
		return rotateRight(n);
	}
	private AvlNode doubleRotateRightLeft(AvlNode n){		/////////////ROTACJA W PRAWO LEWO
		//System.out.println("____________doubleRotateRightLeft__________");
		n.right = rotateRight (n.right);
		return rotateLeft(n);
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////
	private void setBalance(AvlNode cur) {
		  cur.balance = height(cur.right)-height(cur.left);
		 }
	public void remove (String k){
		if (search(root,k)!=null){
			removeFoundNode(search(root,k));
			rotations++;
		}
		else
			System.out.println("jakis blad");
	}
	private void removeFoundNode(AvlNode q){
		AvlNode r;
		if (q.left==null || q.right==null){
			comp++;
			if (q.parent==null){
				this.root=null;
				q=null;
				return;
			}
			r=q;
		} else {
			r=successor(q);
			q.key=r.key;
		}
		AvlNode p;
		comp++;
		if (r.left!=null)
			p=r.left;
		else
			p=r.right;
		
		comp++;
		if (p!=null)
			p.parent=r.parent;
		
		comp++;
		if (r.parent == null)
			this.root=p;
		else {
			comp++;
			if (r==r.parent.left)
				r.parent.left=p;
			else 
				r.parent.right =p;
			recursiveBalance(r.parent);
		}
		r=null;
	}
	 private AvlNode successor(AvlNode q) {
		  if(q.right!=null) {
		   AvlNode r = q.right;
		   while(r.left!=null) {
		    r = r.left;
		   }
		   return r;
		  } else {
		   AvlNode p = q.parent;
		   while(p!=null && q==p.right) {
		    q = p;
		    p = q.parent;
		   }
		   return p;
		  }
		 }
	public void search (String q){
		AvlNode m = search (root,q);

		if (m!=null){
			System.out.print("Key: ["+m.key+"] Parent: ["+m.parent+"] Left: ["+m.left+"] Right: ["+m.right+"] Tlumaczenia: ");
			for (int i =0; i<m.translate.length; i++){
				if (m.translate[i]!=null) {
					System.out.print("[" + m.translate[i] + "] ");
				}
			}
			System.out.println();
		}
	}
	private AvlNode search(AvlNode p, String q){
		if (p == null){
			System.out.println("Nie ma takiego slowa!");
			return null;
		} else {
			comp++;
			if (p.key.compareTo(q)>0){
				AvlNode m = search(p.left,q);
				if (m!=null)
					return m;
				else
					return null;
			} else if (p.key.compareTo(q)<0){
				AvlNode m = search(p.right,q);
				if (m!=null)
					return m;
				else
					return null;
			} else if (p.key.compareTo(q)==0){
					return p;
			}
			  else
				  	return null;
			
		}
	}
	///////////////////////////////////////////////////////////////////////////////////////////////
	public void getTree(){
		if (root != null){
			getTree(root);
		}
	}
	private void getTree(AvlNode node){
			if (node !=null){
				if (node.left !=null && node.right !=null){
					System.out.print("Parent: ["+node.key+"] Left: ["+node.left.key+"] Right: ["+node.right.key+"]");
				} else if (node.left ==null && node.right !=null){
					System.out.print("Parent: ["+node.key+"] Left: [null] Right: ["+node.right.key+"]");
				} else if (node.left !=null && node.right ==null){
					System.out.print("Parent: ["+node.key+"] Left: ["+node.left.key+"] Right: [null]");
				} else {
					System.out.print("Parent: ["+node.key+"] Left: [null] Right: [null]");
				}
				System.out.println();
				
				if (node.left != null)
					getTree(node.left);
				
				if (node.right != null)
					getTree(node.right);
			}
	}
	public void getTreeToFile(String sciezka) throws FileNotFoundException{
		if (sciezka!=null){
			PrintWriter pw = new PrintWriter(sciezka);
			getTreeToFile(root,pw);
			pw.close();
		}
	}
	private void getTreeToFile(AvlNode node, PrintWriter pw){
		if(node.left!=null)
			getTreeToFile(node.left,pw);
		if (node!=null){
			pw.print(node.key);
			for (int i=0; i<node.translate.length;i++){
				if (node.translate[i]!=null) {
					pw.print("/" + node.translate[i]);
				}
			}
			pw.println();
			
		if(node.right!=null)
			getTreeToFile(node.right,pw);
		}
	}
	/////////////////////////////////////////////
	private int maximum(int a, int b) {
		 if(a>=b) {
			 return a;
		  } else {
			  return b;
		  }
	}
	public int height(AvlNode cur) {
		  if(cur==null) {
		   return -1;
		  }
		  if(cur.left==null && cur.right==null) {
		   return 0;
		  } else if(cur.left==null) {
		   return 1+height(cur.right);
		  } else if(cur.right==null) {
		   return 1+height(cur.left);
		  } else {
		   return 1+maximum(height(cur.left),height(cur.right));
		  }
	}
	public int getRotations(){
		return rotations;
	}
	public void resetRotations(){
		rotations =0;
	}
	public int getComp(){
		return comp;
	}
	public void resetComp(){
		comp = 0;
	}
}
