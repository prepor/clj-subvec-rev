package subvec;

import clojure.lang.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SubvecRev extends APersistentVector implements IObj {
    public final IPersistentVector v;
    public final int start;
    public final int end;
    final IPersistentMap _meta;

    public SubvecRev(IPersistentMap meta, IPersistentVector v, int start, int end){
        this._meta = meta;

        if(v instanceof APersistentVector.SubVector)
        {
            APersistentVector.SubVector sv = (APersistentVector.SubVector) v;
            start += sv.start;
            end += sv.start;
            v = sv.v;
        }
        this.v = v;
        this.start = start;
        this.end = end;
    }

    public Iterator iterator(){
        return new Iterator(){
            int i = end;

            public boolean hasNext(){
                return i > start;
            }

            public Object next(){
                if(i > start)
                    return nth(i--);
                else
                    throw new NoSuchElementException();
            }

            public void remove(){
                throw new UnsupportedOperationException();
            }
        };
    }

    public Object nth(int i){
        if((start + i >= end) || (i < 0))
            throw new IndexOutOfBoundsException();
        return v.nth(end - i - 1);
    }

    public IPersistentVector assocN(int i, Object val){
        throw new UnsupportedOperationException();
    }

    public int count(){
        return end - start;
    }

    public IPersistentVector cons(Object o){
        throw new UnsupportedOperationException();
    }

    public IPersistentCollection empty(){
        return PersistentVector.EMPTY.withMeta(meta());
    }

    public IPersistentStack pop(){
        throw new UnsupportedOperationException();
    }

    public SubvecRev withMeta(IPersistentMap meta){
        if(meta == _meta)
            return this;
        return new SubvecRev(meta, v, start, end);
    }

    public IPersistentMap meta(){
        return _meta;
    }
}