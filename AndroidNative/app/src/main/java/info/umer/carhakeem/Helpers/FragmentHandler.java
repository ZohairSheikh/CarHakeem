package info.umer.carhakeem.Helpers;

import androidx.annotation.AnimRes;
import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import info.umer.carhakeem.Interfaces.ICallOnResume;
import info.umer.carhakeem.UI.Fragments.Base;


public class FragmentHandler {

    FragmentManager fragmentManager;
    @IdRes
    int containerId;


    ICallOnResume _ICallOnResume;

    public FragmentHandler(@IdRes int containerId, FragmentActivity context) {
        this.containerId = containerId;
        fragmentManager = context.getSupportFragmentManager();

    }

    public FragmentHandler(@IdRes int containerId, FragmentActivity context, ICallOnResume _iCallOnResume ) {
        this.containerId = containerId;
        fragmentManager = context.getSupportFragmentManager();
        _ICallOnResume = _iCallOnResume;


    }

    public FragmentHandler(@IdRes int containerId, FragmentManager fragmentManager) {
        this.containerId = containerId;
        this.fragmentManager = fragmentManager;
    }

    /*pops the open frament*/
    public void popStack() {
        fragmentManager.popBackStack();
    }

    /*replacing fragment transcation region starts*/
    public void replaceFragment(Base fragment,
                                boolean addToBackStack) {
        addReplaceFragment(fragment, addToBackStack, false, true);
    }

    public void addReplaceFragment(Base fragment,
                                   boolean addToBackStack,
                                   boolean clearBackStack,
                                   boolean replaceFragment) {
        try {
            if (clearBackStack && fragmentManager.getBackStackEntryCount() > 0) {
                FragmentManager.BackStackEntry first = fragmentManager.getBackStackEntryAt(0);
                fragmentManager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if (replaceFragment) {
                fragmentTransaction.replace(containerId, fragment, fragment.getClass().getName());
            } else {
                fragmentTransaction.add(containerId, fragment, fragment.getClass().getName());
            }
            if (addToBackStack) {
                fragmentTransaction.addToBackStack(fragment.getClass().getName());
            }
            fragmentTransaction.commit();


            try {
                if (_ICallOnResume != null) {
                    _ICallOnResume.callOnResume();
                }
            }catch (Exception e)
            {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addReplaceFragmentWithAnimation(Base fragment,
                                                boolean addToBackStack,
                                                boolean replaceFragment,
                                                @AnimRes int enter,
                                                @AnimRes int exit,
                                                @AnimRes int popEnter,
                                                @AnimRes int popExit) {



        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(enter, exit, popEnter, popExit);
        if (replaceFragment) {
            fragmentTransaction.replace(containerId, fragment, fragment.getClass().getName());
        } else {
            fragmentTransaction.add(containerId, fragment, fragment.getClass().getName());
        }
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.getClass().getName());
        }
        fragmentTransaction.commit();

        try {
            if (_ICallOnResume != null) {
                _ICallOnResume.callOnResume();
            }
        }catch (Exception e)
        {

        }
    }




    /*return count of open fragments*/
    public int getBackStackEntryCount() {
        return fragmentManager.getBackStackEntryCount();
    }



}

