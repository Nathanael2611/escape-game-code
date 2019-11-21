package fr.nathanael2611.openclassrooms.escapegame.code.core.util;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;

/**
 * This class was based on the NonNullList Minecraft class
 * @param <E>
 */
public class GivenSizeList<E> extends AbstractList<E>
{
    private final List<E> delegate;
    private final E defaultElement;

    /**
     * Creates a new GivenSizeList with a given size, and filled with the object passed.
     */
    public static <E> GivenSizeList<E> withSize(int size, E fill)
    {
        Object[] aobject = new Object[size];
        Arrays.fill(aobject, fill);
        return new GivenSizeList<E>(Arrays.asList((E[])aobject), fill);
    }

    protected GivenSizeList(List<E> delegateIn, E listType)
    {
        this.delegate = delegateIn;
        this.defaultElement = listType;
    }

    public E get(int p_get_1_)
    {
        return this.delegate.get(p_get_1_);
    }

    public E set(int p_set_1_, E p_set_2_)
    {
        return this.delegate.set(p_set_1_, p_set_2_);
    }

    public void add(int p_add_1_, E p_add_2_)
    {
        this.delegate.add(p_add_1_, p_add_2_);
    }

    public E remove(int p_remove_1_)
    {
        return this.delegate.remove(p_remove_1_);
    }

    public int size()
    {
        return this.delegate.size();
    }

    public void clear()
    {
        if (this.defaultElement == null)
        {
            super.clear();
        }
        else
        {
            for (int i = 0; i < this.size(); ++i)
            {
                this.set(i, this.defaultElement);
            }
        }
    }
}