package ru.job4j.tree;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TreeTest {

    @Test
    public void when6ElFindLastThen6() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test
    public void whenChildExistOnLeafThenNotAdd() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertFalse(tree.add(2, 6));
    }

    @Test
    public void whenRootNull() {
        Tree<Integer> tree = new SimpleTree<>(null);
        tree.add(null, 1);
        tree.add(1, 10);
        assertTrue(
                tree.findBy(10).isPresent()
        );
    }
    @Test
    public void whenAddNull() {
        Tree<Integer> tree = new SimpleTree<>(1);
        assertTrue(tree.findBy(null).isEmpty());
        tree.add(1, 10);
        tree.add(10, null);
        assertTrue(tree.findBy(null).isPresent());
    }

    @Test
    public void whenIsBinaryThenTrue() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 5);
        tree.add(2, 3);
        tree.add(2, 4);
        tree.add(5, 7);
        assertTrue(tree.isBinary());
    }

    @Test
    public void whenIsNotBinaryThenFalse() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 5);
        tree.add(2, 3);
        tree.add(2, 4);
        tree.add(5, 7);
        tree.add(5, 8);
        tree.add(5, 9);
        assertFalse(tree.isBinary());
    }
}