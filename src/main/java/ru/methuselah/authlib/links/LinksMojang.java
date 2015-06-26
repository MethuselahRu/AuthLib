package ru.methuselah.authlib.links;

public final class LinksMojang extends Links
{
	public LinksMojang()
	{
		super("",
			Links.mojangA,
			Links.mojangR,
			Links.mojangV,
			Links.mojangI,
			Links.mojangS,
			Links.mojangJ,
			Links.mojangHJ,
			Links.mojangLJ,
			Links.mojangLHJ,
			Links.mojangAPI1,
			Links.mojangAPI2,
			Links.mojangAPI3,
			Links.mojangAPI4);
		super.setProvider(Links.LinksProvider.mojang);
	}
}
