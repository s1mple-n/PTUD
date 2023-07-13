const tags = document.querySelectorAll('#tags a')

tags.forEach((tag) => {
    tag.addEventListener('click', () => {
        unClick()
        tag.classList.add('visited:text-red-500')
    })
})

function unClick() {
    tags.forEach((tag) => {
        if (tag.classList.contains('visited:text-red-500'))
            tag.classList.remove('visited:text-red-500')
    })
}