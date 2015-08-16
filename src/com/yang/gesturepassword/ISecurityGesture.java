package com.yang.gesturepassword;

import android.app.Activity;

/**
 * 需要手势密码保护的{@link Activity}实现这个接口表明此{@link Activity}需要锁屏。并且要重载{@link Activity}的{@link Activity#onUserInteraction()}的,
 * 在这个方法里调用 {@link GesturePasswordManager#userInteraction()} 即可实现n秒后自动锁屏;
 */
public interface ISecurityGesture {
	
}
