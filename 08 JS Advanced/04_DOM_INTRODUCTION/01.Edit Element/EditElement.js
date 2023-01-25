function editElement(reference, match, replacer) {
    const content = reference.textContent;
    content = content.replaceAll(match, replacer);
   
    reference.textContent = content;

}