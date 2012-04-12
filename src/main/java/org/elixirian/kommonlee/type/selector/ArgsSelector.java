/**
 * 
 */
package org.elixirian.kommonlee.type.selector;

/**
 * <pre>
 *     ___  _____                                              _____
 *    /   \/    / ______ __________________  ______ __ ______ /    /   ______  ______  
 *   /        / _/ __  // /  /   / /  /   /_/ __  // //     //    /   /  ___ \/  ___ \ 
 *  /        \ /  /_/ _/  _  _  /  _  _  //  /_/ _/   __   //    /___/  _____/  _____/
 * /____/\____\/_____//__//_//_/__//_//_/ /_____//___/ /__//________/\_____/ \_____/
 * </pre>
 * 
 * <pre>
 *     ___  _____                                _____
 *    /   \/    /_________  ___ ____ __ ______  /    /   ______  ______
 *   /        / /  ___ \  \/  //___// //     / /    /   /  ___ \/  ___ \
 *  /        \ /  _____/\    //   //   __   / /    /___/  _____/  _____/
 * /____/\____\\_____/   \__//___//___/ /__/ /________/\_____/ \_____/
 * </pre>
 * 
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-11-14)
 * @param <T>
 *          input
 * @param <C>
 *          condition
 * @param <R>
 *          result
 */
public interface ArgsSelector<T, C, R> extends Selector1<T, C, R>, Selector2<T, C, R>, Selector3<T, C, R>,
    Selector4<T, C, R>, Selector5<T, C, R>, VarargsSelector<T, C, R>, ArraySelector<T, C, R>
{
  @Override
  R select(C condition, T first);

  @Override
  R select(C condition, T first, T second);

  @Override
  R select(C condition, T first, T second, T third);

  @Override
  R select(C condition, T first, T second, T third, T fourth);

  @Override
  R select(C condition, T first, T second, T third, T fourth, T fifth);

  @Override
  R select(C condition, T first, T second, T third, T fourth, T fifth, T... rest);

  @Override
  R select(C condition, T[] values);
}