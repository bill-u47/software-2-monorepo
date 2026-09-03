#!/usr/bin/env bash
read -p "Please provide the URL to your GitHub repo: " url
echo -e "Attempting to clone $url"
if git clone $url temp; then
    mv temp/.git .
    rm -rf temp
fi
read -p "Press any key to exit" -n1 -s
