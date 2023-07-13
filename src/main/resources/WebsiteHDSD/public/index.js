const backToTopIcon = document.querySelector(".backToTop");

/**
 * Cai ham ben duoi goi la IIFE -> cu phap dang (function...)()
 */
(
    window.addEventListener("scroll", () => {
        if (window.scrollY > 1300)
            backToTopIcon.style.visibility = "visible";
        else
            backToTopIcon.style.visibility = "hidden";
    })
)()